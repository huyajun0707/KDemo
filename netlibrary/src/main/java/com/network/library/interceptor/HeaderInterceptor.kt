package com.network.library.interceptor


import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date ： 2019-11-20 14:38
 * @depiction ：自定义头部拦截器
 */
class HeaderInterceptor(headerMap: Map<String, () -> String>) : Interceptor {

    private var headerMap: Map<String, () -> String>? = headerMap


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        //获取、修改请求头
        val headers = original.headers()
        val headerRequestBuilder = headers.newBuilder()
        headerMap?.let {
            it.forEach { item ->
                if (!TextUtils.isEmpty(item.value.invoke()))
                    headerRequestBuilder.add(item.key, item.value.invoke())
            }
            val builder = original.newBuilder().headers(headerRequestBuilder.build())
            val request = builder.build()
            return chain.proceed(request)
        }
        return chain.proceed(chain.request())
    }
}