package com.network.library.observer

import android.util.Log
import com.google.gson.JsonParseException
import io.reactivex.observers.DefaultObserver
import org.json.JSONException
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-11-06 10:54
 * @depiction   ： 
 */
open class RxObserver<T>() : DefaultObserver<T>(),
    com.network.library.observer.ObserverInterface {

    private var mListener: com.network.library.listener.HttpOnNextListener<T>? = null

    constructor(listener: Any) : this() {
        this.mListener = listener as com.network.library.listener.HttpOnNextListener<T>
    }

    override fun onStart() {
        mListener?.onStart()
        super.onStart()
    }

    override fun onNext(t: T) {
        mListener?.onNext(t)
    }

    override fun onComplete() {
        mListener?.onComplete()
    }

    override fun onError(e: Throwable) {
        Log.e("---->", "网络请求错误：${e::class.java.simpleName}：${e.message}")
        if (e is HttpException) {     //   HTTP错误
            onException(com.network.library.observer.ExceptionReason.BAD_NETWORK)
        } else if (e is ConnectException
            || e is UnknownHostException
        ) {   //   连接错误
            onException(com.network.library.observer.ExceptionReason.CONNECT_ERROR)
        } else if (e is InterruptedIOException) {   //  连接超时
            onException(com.network.library.observer.ExceptionReason.CONNECT_TIMEOUT)
        } else if (e is JsonParseException
            || e is JSONException
            || e is ParseException
        ) {   //  解析错误
            onException(com.network.library.observer.ExceptionReason.PARSE_ERROR)
        } else {
            onException(com.network.library.observer.ExceptionReason.UNKNOWN_ERROR)
        }
    }


    /**
     * 异常统一处理函数
     */
    override fun onException(exception: com.network.library.observer.ExceptionReason) {
        when (exception) {
            com.network.library.observer.ExceptionReason.CONNECT_ERROR -> mListener?.onError(Throwable("网络连接异常"))
            com.network.library.observer.ExceptionReason.CONNECT_TIMEOUT -> mListener?.onError(Throwable("网络连接超时"))
            com.network.library.observer.ExceptionReason.BAD_NETWORK -> mListener?.onError(Throwable("网络异常"))
            com.network.library.observer.ExceptionReason.PARSE_ERROR -> mListener?.onError(Throwable("数据解析失败"))
            com.network.library.observer.ExceptionReason.UNKNOWN_ERROR -> mListener?.onError(Throwable("未知错误"))
        }
    }

}