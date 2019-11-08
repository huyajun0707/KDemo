package com.dakai.android.network.observer

/**
 * 公共Observer
 * @author      ： CuiYancey <cuiyuancheng0322@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-08-15 13:46
 */
interface ObserverInterface{

    /**
     * 公共异常处理函数
     */
    fun onException(exception: ExceptionReason)
}

/**
 * 请求网络失败原因枚举
 * @author      ： CuiYancey <cuiyuancheng0322@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-08-15 13:48
 */
enum class ExceptionReason {
    /**
     * 解析数据失败
     */
    PARSE_ERROR,
    /**
     * 网络问题
     */
    BAD_NETWORK,
    /**
     * 连接错误
     */
    CONNECT_ERROR,
    /**
     * 连接超时
     */
    CONNECT_TIMEOUT,
    /**
     * 未知错误
     */
    UNKNOWN_ERROR,
}