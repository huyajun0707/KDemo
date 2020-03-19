package com.network.library.retry

import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * 网络请求异常后的重试机制，适用于Flowable类型的网络请求
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-10-24 10:04
 */
class RetryWhenNetworkException2: Function<Flowable<Throwable>, Flowable<*>> {

    //    retry次数
    private val count = 2
    //    延迟
    private val delay: Long = 3000
    //    叠加延迟
    private val increaseDelay: Long = 3000

    override fun apply(flowable: Flowable<Throwable>): Flowable<*> {
        return flowable.zipWith(Flowable.range(1, count + 1),
            BiFunction<Throwable, Int, com.network.library.retry.Wrapper> { throwable, int ->
            //                Log.e("---->", "重试网络请求:$int")
            throwable.printStackTrace()
            //                Log.e("---->", "-->:${throwable.javaClass.simpleName}:${throwable.message}")
                com.network.library.retry.Wrapper(throwable, int)
        }).flatMap(Function<com.network.library.retry.Wrapper, Flowable<*>>{
            return@Function if (it.throwable is ConnectException || it.throwable is SocketTimeoutException || it.throwable is TimeoutException && it.index < count + 1) {
                Flowable.timer(delay + (it.index - 1) * increaseDelay, TimeUnit.MILLISECONDS)
            } else {
//                Log.e("cui_", "错误：${it.throwable.javaClass.simpleName}:${it.throwable.message}")
                Flowable.error<Any>(it.throwable)
            }
        })
    }
}