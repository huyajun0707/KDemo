package com.github.netlibrary.utils

/**
 * @author ： HuYajun <huyajun0707></huyajun0707>@gmail.com>
 * @version ： 1.0
 * @date ： 2019-10-28 14:29
 * @depiction ： Log工具类
 */
class LogUtil private constructor() {

    companion object {
        private var isDebug: Boolean = true

        fun setDebug(debug: Boolean) {
            isDebug = debug
        }

        fun v(tag: String, msg: String) {
            if (isDebug)
                android.util.Log.v(tag, "----->$msg")
        }

        fun v(tag: String, msg: String, t: Throwable) {
            if (isDebug)
                android.util.Log.v(tag, msg, t)
        }

        fun d(tag: String, msg: String) {
            if (isDebug)
                android.util.Log.d(tag, "----->$msg")
        }

        fun d(tag: String, msg: String, t: Throwable) {
            if (isDebug)
                android.util.Log.d(tag, msg, t)
        }

        fun i(tag: String, msg: String) {
            if (isDebug)
                android.util.Log.i(tag, "----->$msg")
        }

        fun i(tag: String, msg: String, t: Throwable) {
            if (isDebug)
                android.util.Log.i(tag, msg, t)
        }

        fun w(tag: String, msg: String) {
            if (isDebug)
                android.util.Log.w(tag, msg)
        }

        fun w(tag: String, msg: String, t: Throwable) {
            if (isDebug)
                android.util.Log.w(tag, msg, t)
        }

        fun e(tag: String, msg: String) {
            if (isDebug)
                android.util.Log.e(tag, "----->$msg")
        }

        fun e(msg: Exception) {
            if (isDebug)
                android.util.Log.e("", "----->${msg.message}")
        }

        fun e(tag: String, msg: String, t: Throwable) {
            if (isDebug)
                android.util.Log.e(tag, msg, t)
        }

        fun print(`object`: Any?) {
            if (isDebug) {
                if (`object` != null) {
                    println("----->$`object`")
                }
            }
        }


    }
}
