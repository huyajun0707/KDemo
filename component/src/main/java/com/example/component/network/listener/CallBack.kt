package com.example.component.network.listener

import androidx.lifecycle.LiveData
import com.dakai.android.network.domain.BaseResponse

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-10-31 20:41
 * @depiction   ：
 */

interface CallBack<T> {

    /**开始网络请求*/
    fun onStart()

    /**获取到网络数据[t]，数据可能为null*/
    fun onLoadedData(t: LiveData<T>?)

    /**网络请求失败，非200情况时调用*/
    fun onFailed(msg: String)

    /**网络请求出错*/
    fun onError(msg:String)

    /**网络请求结束*/
    fun onComplete()
}