package com.dakai.android.permission.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * @author      ： CuiYancey <cuiyuancheng0322@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-09-19 15:30
 * @Describe    ： 权限数据模型
 */
data class PermissionModel(
    /**权限icon，必须授予的权限才需要*/
    val iconRes: Int = 0,
    /**权限*/
    val permission: String? = "",
    val permissionName: String? = "",
    /**需要此权限的原因*/
    val permissionReason: String? = "",
    /**是否永久拒绝*/
    var isPermanentlyDenied: Boolean = false,
    /**是否是必须权限*/
    val isMust: Boolean = false

) : Parcelable {

    /**只传入权限和原因，非必须权限使用此构造方法*/
    constructor(permission: String, permissionReason: String) : this(
        0,
        permission,
        "",
        permissionReason,
        false,
        false
    )

    /**传入权限图标资源id、权限和原因，必须权限使用此构造方法*/
    constructor(iconRes: Int, permission: String, permissionName: String, permissionReason: String) : this(
        iconRes,
        permission,
        permissionName,
        permissionReason,
        false,
        true
    )

    /**不是必须权限*/
    fun isNotMust(): Boolean {
        return !isMust
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(iconRes)
        parcel.writeString(permission)
        parcel.writeString(permissionName)
        parcel.writeString(permissionReason)
        parcel.writeByte(if (isPermanentlyDenied) 1 else 0)
        parcel.writeByte(if (isMust) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PermissionModel> {
        override fun createFromParcel(parcel: Parcel): PermissionModel {
            return PermissionModel(parcel)
        }

        override fun newArray(size: Int): Array<PermissionModel?> {
            return arrayOfNulls(size)
        }
    }


}