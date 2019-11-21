package com.github.netlibrary.retry

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-10-24 10:23
 */
class Wrapper(
    var throwable: Throwable,
    var index: Int
)