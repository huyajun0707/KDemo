package com.github.netlibrary.listener

import android.util.Log
import com.renmai.component.network.BaseResponse

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
        try {
            dataCallback.onBaseDataHandle(baseData as BaseResponse<D>)
        } catch (e: Exception) {
            Log.e("---->",e.message.toString())
            onError(e)
        }

    }

    override fun onError(e: Throwable) {
        dataCallback.onError(e)
    }

    override fun onComplete() {
        super.onComplete()
        dataCallback.onComplete()

    }

}