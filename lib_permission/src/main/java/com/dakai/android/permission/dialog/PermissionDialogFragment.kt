package com.dakai.android.permission.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dakai.android.R
import com.dakai.android.permission.adapter.PermissionAdapter
import com.dakai.android.permission.bean.PermissionModel


/**
 * 参考：https://www.jianshu.com/p/526fcf3e8db3
 * @author      ： CuiYancey <cuiyuancheng0322@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-09-04 13:22
 */
class PermissionDialogFragment: AppCompatDialogFragment() {

    private var dialogTitle: String = ""
    private val permissions: ArrayList<PermissionModel> = ArrayList()
    private var listener: PermissionDialogListener? = null

    companion object{
        private const val CONTENT_KEY = "rationaleContent"
        private const val PERMISSION_LIST_KEY = "permissions"

        fun getPermissionDialog(title: String, permissions: ArrayList<PermissionModel>?): PermissionDialogFragment{
            val dialog = PermissionDialogFragment()
            val bundle = Bundle()
            bundle.putString(CONTENT_KEY, title)
            permissions?.let {
                bundle.putParcelableArrayList(PERMISSION_LIST_KEY, it)
            }
            dialog.arguments = bundle
            return dialog
        }
    }

    fun setPermissionDialogListener(listener: PermissionDialogListener){
        this.listener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //得到页面入参
        arguments?.let {
            dialogTitle = it.getString(CONTENT_KEY, "")
            if (it.containsKey(PERMISSION_LIST_KEY)) {
                permissions.clear()
                val permissionModels: ArrayList<PermissionModel>? = it.getParcelableArrayList(PERMISSION_LIST_KEY)
                permissionModels?.let { list ->
                    permissions.addAll(list)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 这里不设置view，让fragment作为一个control
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // 根据得到的参数，建立一个dialog
        val dialogBuilder = AlertDialog.Builder(activity)
        dialogBuilder.setView(View.inflate(context, R.layout.dialog_permission_must, null))
        return dialogBuilder.create()
    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        // onCreateDialog建立好dialog后，这里可以进行进一步的配置操作
        //防止取消三剑客，必须点按钮
        dialog.setCancelable(false)//1、设置不可取消
        dialog.setCanceledOnTouchOutside(false)//2、、设置点击外部不可取消
        dialog.setOnKeyListener(object : DialogInterface.OnKeyListener{
            override fun onKey(dialog: DialogInterface?, keyCode: Int, event: KeyEvent?): Boolean {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    //3、拦截back键，阻止用户点击back键取消弹窗
                    return true
                }
                return false
            }
        })
    }

    override fun onStart() {
        super.onStart()
        dialog?.let { dialog ->
            dialog.window?.let {
                //给弹窗设置背景（自定义圆角和外边距）
                it.setBackgroundDrawableResource(R.drawable.dialog_permission_bg)
                it.decorView.let { rootView ->
                    val title = rootView.findViewById<TextView>(R.id.tv_dialog_title)
                    val confirmBtn = rootView.findViewById<TextView>(R.id.tv_dialog_btn)
                    val recyclerView = rootView.findViewById<RecyclerView>(R.id.rv_dialog_list)
                    recyclerView.adapter = PermissionAdapter(permissions)
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {})

                    title?.text = dialogTitle
                    confirmBtn.setOnClickListener {
                        listener?.onConfirmBtnClicked()
                        dismissAllowingStateLoss()
                    }
                }
            }
        }
    }

    fun showAllowingStateLoss(manager: FragmentManager?, tag: String?){
        manager?.let {
            val ft = it.beginTransaction()
            ft.add(this, tag)
            ft.commitAllowingStateLoss()
        }

    }

    interface PermissionDialogListener{
        /**
         * 点击肯定按钮回调事件
         */
        fun onConfirmBtnClicked()
    }
}