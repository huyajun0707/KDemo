package com.network.library.listener

import android.util.Log

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-11-06 15:59
 * @depiction   ：
 */

class HttpCallBack<T, D>(val dataCallback: DataCallback<T,D>) : com.network.library.listener.HttpOnNextListener<T>() {
    override fun onStart() {
        dataCallback.onStart()
    }

    override fun onNext(baseData: T?) {
        try {
//            Log.d("---->",baseData.toString())
            dataCallback.onBaseDataHandle(baseData as T)
        } catch (e: Exception) {
            Log.e("---->Type cast failed",e.message.toString())
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