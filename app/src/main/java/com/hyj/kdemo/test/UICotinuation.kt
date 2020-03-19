package com.hyj.kdemo.test

import kotlin.coroutines.Continuation
import kotlin.coroutines.EmptyCoroutineContext

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2020-01-13 00:27
 * @depiction   ：
 */
class UICotinuationWrapper<T>(val continuation: Continuation<T>):Continuation<T> {
    override val context=EmptyCoroutineContext

    override fun resumeWith(result: Result<T>) {
        //切线程
        continuation.resumeWith(result)
    }


}