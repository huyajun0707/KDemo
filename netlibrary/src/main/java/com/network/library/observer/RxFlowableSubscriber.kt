package com.network.library.observer

import com.google.gson.JsonParseException
import io.reactivex.subscribers.DefaultSubscriber
import org.json.JSONException
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * 自定义FlowableSubscriber
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-08-15 13:34
 */
class RxFlowableSubscriber<T>(): DefaultSubscriber<T>(),
    com.network.library.observer.ObserverInterface {

    private var mListener: com.network.library.listener.HttpOnNextListener<T>? = null
    private var mLoadingView: com.network.library.listener.ILoadingView? = null

    constructor(listener: com.network.library.listener.HttpOnNextListener<T>, iLoadingView: com.network.library.listener.ILoadingView):this(){
        this.mListener = listener
        this.mLoadingView = iLoadingView
    }

    override fun onStart() {
        mListener?.onStart()
        mLoadingView?.showLoading()
        //2019-08-15  在这里调用 request(long)，super内有默认实现
        super.onStart()
    }

    override fun onNext(t: T) {
        mListener?.onNext(t)
    }

    override fun onComplete() {
        mListener?.onComplete()
        mLoadingView?.hideLoading()
    }

    override fun onError(t: Throwable?) {
        t?.let {
            if (it is HttpException) {     //   HTTP错误
                onException(com.network.library.observer.ExceptionReason.BAD_NETWORK)
            } else if (it is ConnectException
                || it is UnknownHostException
            ) {   //   连接错误
                onException(com.network.library.observer.ExceptionReason.CONNECT_ERROR)
            } else if (it is InterruptedIOException) {   //  连接超时
                onException(com.network.library.observer.ExceptionReason.CONNECT_TIMEOUT)
            } else if (it is JsonParseException
                || it is JSONException
                || it is ParseException
            ) {   //  解析错误
                onException(com.network.library.observer.ExceptionReason.PARSE_ERROR)
            } else {
                onException(com.network.library.observer.ExceptionReason.UNKNOWN_ERROR)
            }
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