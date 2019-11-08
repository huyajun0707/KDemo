package com.dakai.android.network.observer

import com.dakai.android.network.listener.HttpOnNextListener
import com.example.component.network.listener.ILoadingView
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
 * @author      ： CuiYancey <cuiyuancheng0322@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-08-15 13:34
 */
class RxFlowableSubscriber<T>(): DefaultSubscriber<T>(), ObserverInterface {

    private var mListener: HttpOnNextListener<T>? = null
    private var mLoadingView: ILoadingView? = null

    constructor(listener: HttpOnNextListener<T>,iLoadingView: ILoadingView):this(){
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
                onException(ExceptionReason.BAD_NETWORK)
            } else if (it is ConnectException
                || it is UnknownHostException
            ) {   //   连接错误
                onException(ExceptionReason.CONNECT_ERROR)
            } else if (it is InterruptedIOException) {   //  连接超时
                onException(ExceptionReason.CONNECT_TIMEOUT)
            } else if (it is JsonParseException
                || it is JSONException
                || it is ParseException
            ) {   //  解析错误
                onException(ExceptionReason.PARSE_ERROR)
            } else {
                onException(ExceptionReason.UNKNOWN_ERROR)
            }
        }

    }

    /**
     * 异常统一处理函数
     */
    override fun onException(exception: ExceptionReason) {
        when (exception) {
            ExceptionReason.CONNECT_ERROR -> mListener?.onError(Throwable("网络连接异常"))
            ExceptionReason.CONNECT_TIMEOUT -> mListener?.onError(Throwable("网络连接超时"))
            ExceptionReason.BAD_NETWORK -> mListener?.onError(Throwable("网络异常"))
            ExceptionReason.PARSE_ERROR -> mListener?.onError(Throwable("数据解析失败"))
            ExceptionReason.UNKNOWN_ERROR -> mListener?.onError(Throwable("未知错误"))
        }
    }
}