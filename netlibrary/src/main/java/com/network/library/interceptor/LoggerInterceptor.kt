package com.network.library.interceptor

import android.text.TextUtils
import android.util.Log
import com.network.library.utils.LogUtil
import okhttp3.*
import okio.Buffer
import java.io.IOException

/**
 * @author ： HuYajun <huyajun0707></huyajun0707>@gmail.com>
 * @version ： 1.0
 * @date ： 2019-11-18 10:43
 * @depiction ： 自定义日志拦截器
 */

class LoggerInterceptor @JvmOverloads constructor(
    tag: String,
    private val showResponse: Boolean = false
) : Interceptor {
    private val tag: String

    init {
        var tag = tag
        if (TextUtils.isEmpty(tag)) {
            tag = com.network.library.interceptor.LoggerInterceptor.Companion.TAG
        }
        this.tag = tag
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val request = chain.request()
        logForRequest(request)
        val response = chain.proceed(request)

        return logForResponse(response)
    }

    private fun logForRequest(request: Request) {
        try {
            val url = request.url().toString()
            val headers = request.headers()

            Log.e(tag, "---------------------request log start---------------------")
            Log.e(tag, "method : " + request.method())
            Log.e(tag, "url : $url")
            if (headers != null && headers.size() > 0) {
                Log.e(tag, "headers : \n")
                Log.e(tag, headers.toString())
            }
            val requestBody = request.body()
            if (requestBody != null) {
                val mediaType = requestBody.contentType()
                if (mediaType != null) {
                    Log.e(tag, "contentType : $mediaType")
                    if (isText(mediaType)) {
                        Log.e(tag, "content : " + bodyToString(request))
                    } else {
                        Log.e(
                            tag,
                            "content : " + " maybe [ic_file part] , too large too print , ignored!"
                        )
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            Log.e(tag, "---------------------request log end-----------------------")
        }
    }

    private fun logForResponse(response: Response): Response? {
        try {
            Log.e(tag, "---------------------response log start---------------------")
            val builder = response.newBuilder()
            val clone = builder.build()
            Log.e(tag, "url : " + clone.request().url())
            Log.e(tag, "code : " + clone.code())
            Log.e(tag, "protocol : " + clone.protocol())
            if (!TextUtils.isEmpty(clone.message()))
                Log.e(tag, String.format("message : %s", clone.message()))

            if (showResponse) {
                var body = clone.body()
                if (body != null) {
                    val mediaType = body.contentType()
                    if (mediaType != null) {
                        Log.e(tag, "contentType : $mediaType")
                        if (isText(mediaType)) {
                            val resp = body.string()
                            Log.e(tag, "content : $resp")
                            body = ResponseBody.create(mediaType, resp)
                            return response.newBuilder().body(body).build()
                        } else {
                            Log.e(
                                tag,
                                "content : " + " maybe [ic_file part] , too large too print , ignored!"
                            )
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.message?.let { LogUtil.e("logForResponse", it) }
        } finally {
            Log.e(tag, "---------------------response log end-----------------------")
        }

        return response
    }

    private fun isText(mediaType: MediaType): Boolean {
        if (mediaType.type() != null && mediaType.type() == "text") {
            return true
        }
        if (mediaType.subtype() != null) {
            if (mediaType.toString() == "application/x-www-form-urlencoded" ||
                mediaType.subtype() == "json" ||
                mediaType.subtype() == "xml" ||
                mediaType.subtype() == "html" ||
                mediaType.subtype() == "webviewhtml"
            )
            //
                return true
        }
        return false
    }

    private fun bodyToString(request: Request): String {
        try {
            val copy = request.newBuilder().build()
            val buffer = Buffer()
            copy.body()!!.writeTo(buffer)
            return buffer.readUtf8()
        } catch (e: IOException) {
            return "something error when show requestBody."
        }

    }

    companion object {
        val TAG = "NetworkEngine"
    }
}
