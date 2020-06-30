package com.network.library.listener



/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-09-24 18:06
 * @Describe    ： 数据回调接口
 */
interface DataCallback<T,D> {

    /**开始网络请求*/
    fun onStart()

    /**接口返回的BaseResponse<D>数据的处理逻辑*/
    fun onBaseDataHandle(baseData: T?)

    /**获取到网络数据[t]，数据可能为null*/
    fun onLoadedData(data: D?)

    /**网络请求失败，非200情况时调用*/
    fun onFailed(code: String, msg: String)

    /**网络请求出错*/
    fun onError(e: Throwable)

    /**网络请求结束*/
    fun onComplete()
}

