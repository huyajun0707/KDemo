package com.hyj.kdemo.test

import java.util.concurrent.Executors

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2020-01-13 00:18
 * @depiction   ：
 */
private val pool by lazy {
    Executors.newCachedThreadPool()
}

open class AsyncTask(val block:()->Unit) {
    fun execute() = pool.execute(block)
}