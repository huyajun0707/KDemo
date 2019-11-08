package com.dakai.android.permission.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dakai.android.R
import com.dakai.android.permission.bean.PermissionModel


/**
 * @author      ： CuiYancey <cuiyuancheng0322@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-09-20 18:39
 * @Describe    ： 权限列表adapter
 */
class PermissionAdapter(data: ArrayList<PermissionModel>) :
    RecyclerView.Adapter<PermissionViewHolder>() {

    private var mData: ArrayList<PermissionModel> = ArrayList()

    init {
        this.mData = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PermissionViewHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_permission, parent, false)
        return PermissionViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return mData.count()
    }

    override fun onBindViewHolder(holder: PermissionViewHolder, position: Int) {
        holder.permissionPic?.setImageResource(mData[position].iconRes)
        holder.permissionName?.text = mData[position].permissionName
        holder.permissionReason?.text = mData[position].permissionReason
    }
}

class PermissionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var permissionPic: ImageView? = null
    var permissionName: TextView? = null
    var permissionReason: TextView? = null

    init {
        permissionPic = view.findViewById(R.id.iv_permission_pic)
        permissionName = view.findViewById(R.id.tv_permission_name)
        permissionReason = view.findViewById(R.id.tv_permission_reason)
    }

}