package com.dakai.android.permission

/**
 * 权限检查回调
 * @author      ： CuiYancey <cuiyuancheng0322@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-09-02 18:27
 */
interface PermissionCallback {
    /**
     * 同意授权
     */
    fun onPermissionGranted()
}

interface PermissionCompatCallback {

    /**
     * 同意授权
     */
    fun onPermissionGranted(permission: String)

    /**
     * 拒绝授权
     */
    fun onPermissionDenied(permission: String)

    /**
     * 拒绝权限并选择不再询问（永久拒绝）
     */
    fun somePermissionPermanentlyDenied(permission: String)

    /**
     * 所有的权限都申请过之后
     */
    fun afterAllPermissionRequest()
}