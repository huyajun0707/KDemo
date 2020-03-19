package com.renmai.component.network

import java.io.Serializable

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-10-31 14:17
 * @depiction   ：
 */
data class BaseResponse<T>(
    var code: String?,
    val data: T?,
    val msg: String?,
    val success: Boolean ?
) : Serializable {
    constructor() : this("99999", null, "未知错误", false)

    override fun toString(): String {
        return "BaseResponse(code=$code, data=$data, msg=$msg, success=$success)"
    }

//    override fun toString(): String {
//        return "BaseResponse(code='$code', data=$data, msg='$msg', success=$success)"
//    }
}