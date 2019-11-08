package com.example.component.network.listener

import com.dakai.android.network.domain.BaseResponse
import com.dakai.android.network.listener.HttpOnNextListener

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-11-06 15:59
 * @depiction   ：
 */

class HttpCallBack<T, D>(val dataCallback: DataCallback<D>) : HttpOnNextListener<T>() {
    override fun onStart() {
        dataCallback.onStart()
    }

    override fun onNext(baseData: T?) {
        dataCallback.onBaseDataHandle(baseData as BaseResponse<D>)

    }

    override fun onError(e: Throwable) {
        dataCallback.onError(e)
    }

    override fun onComplete() {
        super.onComplete()
        dataCallback.onComplete()

    }

}