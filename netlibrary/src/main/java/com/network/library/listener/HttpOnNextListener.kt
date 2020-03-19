package com.network.library.listener


/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-11-06 14:21
 * @depiction   ：
 */
abstract class HttpOnNextListener<T> {

    /**
     * 事件开始回调，建议在此函数中显示loading
     */
    abstract fun onStart()

    /**
     * 成功后回调方法
     * @param baseData
     */
    abstract fun onNext(baseData: T?)

    /**
     * 事件完成回调，建议在此函数中结束loading，展示内容
     */
    open fun onComplete() = Unit

    /**
     * 失败或者错误方法，建议在此函数中显示错误提示
     * @param e
     */
    abstract fun onError(e: Throwable)

    /**
     * 取消回調
     */
    open fun onCancel() = Unit
}