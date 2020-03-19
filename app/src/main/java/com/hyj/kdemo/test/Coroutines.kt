package com.hyj.kdemo.test

import com.hyj.kdemo.entity.IndexStatus
import com.hyj.kdemo.net.ApiService
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.startCoroutine
import kotlin.coroutines.suspendCoroutine

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2020-01-12 23:16
 * @depiction   ：
 */

fun 开始协程(block: suspend () -> Unit) {
    block.startCoroutine(ContextContinuation(AsyncContext()))
}


suspend fun <T> 我要开始耗时操作了(block: suspend () -> T) = suspendCoroutine<T> { continuation ->
    val uicontinuation = UICotinuationWrapper(continuation)
    AsyncTask {
        try {
//            var result =block()
//            continuation.resume(result)

        } catch (e: Exception) {
            continuation.resumeWithException(e)
        }
    }
}

suspend fun 我要开始请求数据了(): IndexStatus {
    val responseBody = ApiService.instance.test().execute()
    if (responseBody.isSuccessful) {
        responseBody.body()?.data?.let {
            //应该切为主线程
            return it
        }
        throw Exception(Exception("网络异常"))
    } else {
        throw Exception(Exception("网络异常"))
    }

}

//suspend fun 我要开始请求数据了() = suspendCoroutine<IndexStatus> { continuation ->
//    val uicontinuation = UICotinuationWrapper(continuation)
//    AsyncTask {
//        try {
//            val responseBody = ApiService.instance.test().execute()
//            if (responseBody.isSuccessful) {
//                responseBody.body()?.data?.let {
//                    //应该切为主线程
//                    uicontinuation::resume }
//            } else {
//                uicontinuation.resumeWithException(Exception("网络异常"))
//            }
//
//        } catch (e: Exception) {
//            continuation.resumeWithException(e)
//        }
//    }
//}