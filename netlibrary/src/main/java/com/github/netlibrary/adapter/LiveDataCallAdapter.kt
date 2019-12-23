package com.github.netlibrary.cache.adapter

import androidx.lifecycle.LiveData
import com.github.netlibrary.observer.ExceptionReason
import com.github.netlibrary.utils.LogUtil
import com.google.gson.JsonParseException
import com.renmai.component.network.BaseResponse
import org.json.JSONException
import retrofit2.*
import java.io.InterruptedIOException
import java.lang.reflect.Type
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.ParseException
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-10-29 20:39
 * @depiction   ：
 */

class LiveDataCallAdapter<T>(private val responseType: Type) : CallAdapter<T, LiveData<T>> {
    override fun adapt(call: Call<T>): LiveData<T> {
        return object : LiveData<T>() {
            private val started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {//确保执行一次
                    call.enqueue(object : Callback<T> {
                        override fun onFailure(call: Call<T>, e: Throwable) {

                            var value: T
                            LogUtil.e(
                                "LiveDataCallAdapter",
                                "网络请求错误：${e::class.java.simpleName}：${e.message}"
                            )
                            if (e is HttpException) {     //   HTTP错误
                                value = onException(ExceptionReason.BAD_NETWORK)
                            } else if (e is ConnectException
                                || e is UnknownHostException
                            ) {   //   连接错误
                                value = onException(ExceptionReason.CONNECT_ERROR)
                            } else if (e is InterruptedIOException) {   //  连接超时
                                value = onException(ExceptionReason.CONNECT_TIMEOUT)
                            } else if (e is JsonParseException
                                || e is JSONException
                                || e is ParseException
                            ) {   //  解析错误
                                value = onException(ExceptionReason.PARSE_ERROR)
                            } else {
                                value = onException(ExceptionReason.UNKNOWN_ERROR)
                            }
                            postValue(value)
                        }

                        override fun onResponse(call: Call<T>, response: Response<T>) {
                            postValue(response.body())
                        }
                    })
                }
            }
        }
    }

    override fun responseType() = responseType
    /**
     * 异常统一处理函数
     */
    fun <T> onException(exception: ExceptionReason): T {
        when (exception) {
            ExceptionReason.CONNECT_ERROR ->
                return BaseResponse<Any>("99999", null, "网络连接异常", false) as T
            ExceptionReason.CONNECT_TIMEOUT ->
                return BaseResponse<Any>("99999", null, "网络连接超时", false) as T
            ExceptionReason.BAD_NETWORK ->
                return BaseResponse<Any>("99999", null, "网络异常", false) as T
            ExceptionReason.PARSE_ERROR ->
                return BaseResponse<Any>("99999", null, "数据解析失败", false) as T
            ExceptionReason.UNKNOWN_ERROR ->
                return BaseResponse<Any>("99999", null, "未知错误", false) as T
        }
    }


}