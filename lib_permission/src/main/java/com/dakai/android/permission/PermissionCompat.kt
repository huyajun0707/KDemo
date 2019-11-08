package com.dakai.android.permission

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.Size
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.dakai.android.permission.bean.PermissionModel
import com.dakai.android.permission.dialog.PermissionDialogFragment
import java.lang.StringBuilder
import java.lang.ref.WeakReference


/**
 * 权限处理类
 * @author      ： CuiYancey <cuiyuancheng0322@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-09-02 18:37
 */
class PermissionCompat(activity: FragmentActivity) : PermissionCompatCallback,
    PermissionDialogFragment.PermissionDialogListener {

    override fun onConfirmBtnClicked() {
        requestPermission()
    }

    override fun onPermissionGranted(permission: String) {
        //移除授权的权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            permissions.removeIf { it.permission == permission }
        } else {
            permissions.removeAll(permissions.filter { it.permission == permission })
        }
    }

    override fun onPermissionDenied(permission: String) {
        //权限被拒，移除非必须权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            permissions.removeIf {
                it.permission == permission && it.isNotMust()
            }
        } else {
            permissions.removeAll(permissions.filter { it.permission == permission && it.isNotMust() })
        }
    }

    override fun somePermissionPermanentlyDenied(permission: String) {
        //权限被永久拒绝，1、非必须权限->移除  2、必须权限->设置isPermanentlyDenied为true
        permissions.forEachIndexed { index, permissionModel ->
            if (permissionModel.permission == permission) {
                if (permissionModel.isNotMust()) {
                    permissions.removeAt(index)
                } else {
                    permissionModel.isPermanentlyDenied = true
                }
            }
        }
    }

    override fun afterAllPermissionRequest() {
        if (permissions.isNotEmpty()) {//权限请求列表中仍有需要授权的权限
            if (permissions.any { it.isPermanentlyDenied }) {
                //1、如果有任何一个权限被永久拒绝，限制弹窗一次，后续不会再弹窗，而是直接跳转应用详情设置页
                if (permissions.any { !it.isPermanentlyDenied }) {
                    //开始计数，记录连续拒绝次数
                    sequentialRejectCount++
                    if (sequentialRejectCount == MAX_SEQUENTIAL_REJECT_COUNT) {
                        //连续拒绝次数达到最大连续拒绝次数，直接进入应用详情设置页
                        goAppDetailSetting()
                    } else {
                        //1.1、连续拒绝次数未达到最大连续拒绝次数，且存在非永久拒绝的权限，弹窗
                        showMustPermissionDialog()
                    }
                } else {
                    //1.2、都是被永久拒绝的权限，进入应用详情设置页
                    goAppDetailSetting()
                }
            } else {
                //2、没有被永久拒绝的权限，无限弹窗请求，不会自动跳转应用详情设置页
                showMustPermissionDialog()
            }
        }
    }

    private fun showMustPermissionDialog() {
        weakActivity?.get()?.let {
            dialog.showAllowingStateLoss(it.supportFragmentManager, "PermissionDialogFragment")
        }
    }

    /**
     * 跳转应用详情设置页，目前之后应用必须授权的权限才会跳转，
     */
    private fun goAppDetailSetting() {
        weakActivity?.get()?.let {
            //连续拒绝次数归零
            sequentialRejectCount = 0
            //后期如果把SettingUtils提取出来了，就用SettingUtils中的方法
            val intent = Intent()
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS").data = Uri.fromParts("package", it.packageName, null)
            it.startActivity(intent)
            //结束进程，退出当前应用，还会在后台列表中（当你栈里只有一个Activity的时候，这个措施是行之有效的：https://www.jianshu.com/p/1adb4a6b8618）
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }

    private var weakActivity: WeakReference<FragmentActivity>? = null
    private var callback: PermissionCallback? = null
    /**需要授权的权限集合*/
    private val permissions: ArrayList<PermissionModel> = ArrayList()
    /**连续拒绝次数*/
    private var sequentialRejectCount = 0
    private val dialog = PermissionDialogFragment.getPermissionDialog("授予以下权限才能正常使用", permissions)

    init {
        weakActivity = WeakReference(activity)
        callback = null
        permissions.clear()
        //初始化时给弹窗设置监听，避免多次重新设置
        dialog.setPermissionDialogListener(this)
    }

    /**
     * 设置[callback]权限授权结果回调
     */
    fun setCallBack(callback: PermissionCallback): PermissionCompat {
        this.callback = callback
        return this
    }

    /**
     * 添加应用必须权限，最好在splash页面使用，否则跳转应用详情设置页后不能正常杀死进程
     * [permissionIconRes]权限图标资源id
     * [permission]权限
     * [permissionName]权限名称
     * [rationale]需要此权限的原因说明
     */
    fun addMustPermission(
        @DrawableRes permissionIconRes: Int, permission: String,
        permissionName: String,
        rationale: String
    ): PermissionCompat {
        permissions.add(PermissionModel(permissionIconRes, permission, permissionName, rationale))
        return this
    }

    /**
     * 添加可选权限
     * [permission]权限
     * [rationale]需要此权限的原因说明
     */
    fun addPermission(permission: String, rationale: String): PermissionCompat {
        permissions.add(PermissionModel(permission, rationale))
        return this
    }

    /**
     * 请求APP非必须权限
     */
    fun requestPermission() {
        //要申请的权限
        val builder = StringBuilder()
        permissions.forEach {
            builder.append(it.permission)
            builder.append(DELIMITERS)
        }
        val permissions = builder.toString().split(DELIMITERS).filter { it.isNotEmpty() }.toTypedArray()

        if (isNotEmptyPermissions(permissions)) {
            weakActivity?.let {
                if (hasPermissions(it.get()!!, *permissions)) {
                    //已拥有权限，回调授权成功
                    callback?.onPermissionGranted()
                    return
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    // 当api大于等于23时，才进行权限申请
                    requestPermissions(it.get()!!, callback, permissions)
                } else {
                    // 当api小于23时，直接返回权限请求成功
                    callback?.onPermissionGranted()
                }
            }
        } else {
            //当请求权限为空时，直接返回权限请求成功
            callback?.onPermissionGranted()
        }
    }

    /**
     * 判断权限是否已经授权（用的EasyPermissions的方法）
     * [context]上下文环境
     * [perms]  权限
     */
    private fun hasPermissions(context: Context, @Size(min = 1) vararg perms: String): Boolean {
        // Always return true for SDK < M, let the system deal with the permissions
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Log.w(TAG, "hasPermissions: API version < M, returning true by default")
            // DANGER ZONE!!! Changing this will break the library.
            return true
        }

        for (perm in perms) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    perm
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    /**
     * 请求权限不为空（例如：空字符串""）
     */
    private fun isNotEmptyPermissions(permissions: Array<out String>): Boolean {
        return permissions.filter {
            !TextUtils.isEmpty(it)
        }.count() > 0
    }

    /**
     * 请求权限
     */
    private fun requestPermissions(
        activity: FragmentActivity,
        callback: PermissionCallback?,
        permissions: Array<out String>
    ) {
        val fm: FragmentManager = activity.supportFragmentManager
        getPermissionFragment(fm)?.let { fragment ->
            //设置权限请求回调
            fragment.setCallback(callback)
            //使用PermissionsFragment请求权限
            fragment.requestPermissions(permissions, PERMISSION_REQUEST_CODE)
            fragment.setCompatCallback(this)

        }
    }

    /**
     * 获取权限请求Fragment
     */
    private fun getPermissionFragment(fm: FragmentManager): PermissionsFragment? {
        var fragment: PermissionsFragment? =
            fm.findFragmentByTag(PERMISSION_TAG) as PermissionsFragment?
        if (fragment == null) {
            //开启事务，添加fragment
            fragment = PermissionsFragment()
            fm.beginTransaction().add(fragment, PERMISSION_TAG)
                .commitAllowingStateLoss()
        }
        fm.executePendingTransactions()
        return fragment
    }

    companion object {

        private const val PERMISSION_TAG = "permissionFragment"
        private const val TAG = "PermissionCompat"
        private const val PERMISSION_REQUEST_CODE = 1101
        /**最大连续拒绝次数*/
        private const val MAX_SEQUENTIAL_REJECT_COUNT = 2
        /**StringBuilder分隔符*/
        private const val DELIMITERS = ","

        @JvmStatic
        fun with(activity: FragmentActivity): PermissionCompat {
            return PermissionCompat(activity)
        }
    }
}