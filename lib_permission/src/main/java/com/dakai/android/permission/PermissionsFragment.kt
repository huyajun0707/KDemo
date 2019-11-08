package com.dakai.android.permission

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

/**
 * @author      ： CuiYancey <cuiyuancheng0322@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-09-02 18:20
 */
class PermissionsFragment : Fragment() {

    fun setCallback(callback: PermissionCallback?) {
        this.callback = callback
    }

    fun setCompatCallback(compatCallback: PermissionCompatCallback) {
        this.compatCallback = compatCallback
    }

    /**
     * 权限检查回调
     */
    private var callback: PermissionCallback? = null
    /**
     * PermissionCompat回调
     */
    private var compatCallback: PermissionCompatCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 禁止横竖屏切换时的Fragment的重建
        retainInstance = true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (i in permissions.indices) {
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                //权限被拒，判断是不是选择了不再询问(用户拒绝了权限请求，并在权限请求系统对话框中选择了 Don’t ask again 选项，shouldShowRequestPermissionRationale将返回 false)
                if (!ActivityCompat.shouldShowRequestPermissionRationale(
                        activity as Activity,
                        permissions[i]
                    )
                ) {
                    //拒绝授权 + 不再询问 = 永久拒绝
                    compatCallback?.somePermissionPermanentlyDenied(permissions[i])
                } else {
                    //拒绝授权
                    compatCallback?.onPermissionDenied(permissions[i])
                }
            } else {
                //同意授权
                compatCallback?.onPermissionGranted(permissions[i])
            }
        }
        compatCallback?.afterAllPermissionRequest()
    }
}