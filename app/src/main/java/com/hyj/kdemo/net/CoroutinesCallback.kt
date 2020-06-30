package com.hyj.kdemo.net

import android.os.Handler
import android.os.Looper
import com.google.gson.JsonParseException
import com.network.library.listener.DataCallback
import com.network.library.listener.ILoadingView
import com.renmai.component.network.BaseResponse
import kotlinx.coroutines.*
import org.json.JSONException
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.ParseException
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2020-01-07 18:49
 * @depiction   ： 网络请求统一处理
 */


val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

}

private val handler = Handler(Looper.getMainLooper())

//用拦截器实现code处理 并切换线程
class NetWorkContinuationInterceptor(val view: ILoadingView?) : ContinuationInterceptor {
    override val key = ContinuationInterceptor
    override fun <T> interceptContinuation(continuation: Continuation<T>) =
        NetWorkContinuation(continuation, view)
}

class NetWorkContinuation<T>(val continuation: Continuation<T>, val view: ILoadingView?) :
    Continuation<T> {
    override val context = continuation.context

    override fun resumeWith(result: Result<T>) {
        result.onSuccess { it ->
            if (it != null) {
                try {
                    var baseResponse = it as BaseResponse<*>
                    if (baseResponse != null) {
                        println("--->code:${baseResponse.code}")
                        if (baseResponse.code.equals("200")) {
                            //展示数据
                            handler.post(Runnable {
                                continuation.resumeWith(result)
                            })
                            return@resumeWith
                        } else {
                            handler.post(Runnable {
                                view?.showFailureMessage(baseResponse.msg!!)
                            })
                            return@resumeWith
                        }
                    } else {
                        //BaseResponse为空，显示获取数据失败视图
                        handler.post(Runnable {
                            view?.showFailureMessage("未获取到网络数据")
                        })
                        return@resumeWith
                    }
                } catch (e: java.lang.Exception) {

                }
            }

        }
        continuation.resumeWith(result)
    }
}


fun CoroutineScope.safeLoaddingLaunch(
    view: ILoadingView?,
    block: suspend () -> Unit
): Job = launch(NetWorkContinuationInterceptor(view)) {
    try {
        view?.showLoading()
        block()
    } catch (e: Exception) {
        handlerException(e, view)
    } finally {
        view?.hideLoading()
    }
}


fun <T> CoroutineScope.safeNoLoaddingLaunch(
    view: ILoadingView,
    block: suspend () -> Unit
): Job = launch(NetWorkContinuationInterceptor(view)) {
    try {
        block()
    } catch (e: Exception) {
        handlerException(e, view)
    } finally {
    }

}

//
//fun <T> CoroutineScope.safeLaunch(
//    block: suspend () -> BaseResponse<T>,
//    dataCallback: com.network.library.listener.DataCallback<T>
//): Job = launch(Dispatchers.Main) {
//    try {
//        dataCallback.onStart()
//        var baseResponse = block()
//        dataCallback.onBaseDataHandle(baseResponse)
//    } catch (e: Exception) {
//        if (e is HttpException) {     //   HTTP错误
//            onException(
//                dataCallback,
//                com.network.library.observer.ExceptionReason.BAD_NETWORK
//            )
//        } else if (e is ConnectException
//            || e is UnknownHostException
//        ) {   //   连接错误
//            onException(
//                dataCallback,
//                com.network.library.observer.ExceptionReason.CONNECT_ERROR
//            )
//        } else if (e is InterruptedIOException) {   //  连接超时
//            onException(
//                dataCallback,
//                com.network.library.observer.ExceptionReason.CONNECT_TIMEOUT
//            )
//        } else if (e is JsonParseException
//            || e is JSONException
//            || e is ParseException
//        ) {   //  解析错误
//            onException(
//                dataCallback,
//                com.network.library.observer.ExceptionReason.PARSE_ERROR
//            )
//        } else {
//            onException(
//                dataCallback,
//                com.network.library.observer.ExceptionReason.UNKNOWN_ERROR
//            )
//        }
//
//    } finally {
//        dataCallback.onComplete()
//    }
//
//}


/**接口返回的BaseResponse<D>数据的处理逻辑*/


/**
 * 异常统一处理函数
 */
fun <T,D> onException(
    dataCallback: DataCallback<T,D>,
    exception: com.network.library.observer.ExceptionReason
) {
    when (exception) {
        com.network.library.observer.ExceptionReason.CONNECT_ERROR -> dataCallback?.onError(
            Throwable("网络连接异常")
        )
        com.network.library.observer.ExceptionReason.CONNECT_TIMEOUT -> dataCallback?.onError(
            Throwable("网络连接超时")
        )
        com.network.library.observer.ExceptionReason.BAD_NETWORK -> dataCallback?.onError(
            Throwable(
                "网络异常"
            )
        )
        com.network.library.observer.ExceptionReason.PARSE_ERROR -> dataCallback?.onError(
            Throwable(
                "数据解析失败"
            )
        )
        com.network.library.observer.ExceptionReason.UNKNOWN_ERROR -> dataCallback?.onError(
            Throwable("未知错误")
        )
    }
}


fun handlerException(e: Exception, view: ILoadingView?) {
    println("-->${e.message}")
    if (e is HttpException) {     //   HTTP错误
        onCallException(view, com.network.library.observer.ExceptionReason.BAD_NETWORK)
    } else if (e is ConnectException
        || e is UnknownHostException
    ) {   //   连接错误
        onCallException(view, com.network.library.observer.ExceptionReason.CONNECT_ERROR)
    } else if (e is InterruptedIOException) {   //  连接超时
        onCallException(view, com.network.library.observer.ExceptionReason.CONNECT_TIMEOUT)
    } else if (e is JsonParseException
        || e is JSONException
        || e is ParseException
    ) {   //  解析错误
        onCallException(view, com.network.library.observer.ExceptionReason.PARSE_ERROR)
    } else {
        onCallException(view, com.network.library.observer.ExceptionReason.UNKNOWN_ERROR)
    }
}

/**
 * 异常统一处理函数
 */
fun onCallException(
    veiw: ILoadingView?,
    exception: com.network.library.observer.ExceptionReason
) {
    when (exception) {
        com.network.library.observer.ExceptionReason.CONNECT_ERROR -> veiw?.showErrorMessage(
            "网络连接异常"
        )
        com.network.library.observer.ExceptionReason.CONNECT_TIMEOUT -> veiw?.showErrorMessage(
            "网络连接超时"
        )
        com.network.library.observer.ExceptionReason.BAD_NETWORK -> veiw?.showErrorMessage("网络异常")
        com.network.library.observer.ExceptionReason.PARSE_ERROR -> veiw?.showErrorMessage("数据解析失败")
        com.network.library.observer.ExceptionReason.UNKNOWN_ERROR -> veiw?.showErrorMessage(
            "未知错误"
        )
    }
}


