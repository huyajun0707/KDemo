package com.hyj.kdemo.test

import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2020-01-13 00:36
 * @depiction   ：
 */
open class AsyncContext:AbstractCoroutineContextElement(ContinuationInterceptor),
    ContinuationInterceptor {
    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        return UICotinuationWrapper(continuation.context.fold(continuation){
            continuation,element  ->
            if(element!=this && (element is ContinuationInterceptor)){
                    element.interceptContinuation(continuation)
            }else
                continuation

        })
    }
}