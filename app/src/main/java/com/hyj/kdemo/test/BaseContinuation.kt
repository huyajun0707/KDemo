package com.hyj.kdemo.test

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2020-01-12 23:19
 * @depiction   ：
 */
open class BaseContinuation :Continuation<Unit>{
    override val context: CoroutineContext=EmptyCoroutineContext

    override fun resumeWith(result: Result<Unit>) {
    }

}