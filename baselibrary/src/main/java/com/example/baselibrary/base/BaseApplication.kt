package com.example.baselibrary.base

import android.app.Application
import com.example.component.utils.AppUtil

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-10-28 11:09
 * @depiction   ：
 */
open class BaseApplication : Application() {


    companion object {
        private var instance: Application? = null
        fun instance() = instance!!
    }



    override fun onCreate() {
        super.onCreate()
        instance = this
        AppUtil.getInstance().application = this
    }

}

