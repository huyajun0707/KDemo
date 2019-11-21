package com.renmai.baselibrary.base

import android.app.Application
import com.renmai.component.utils.AppUtil
import com.tencent.mmkv.MMKV

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
        MMKV.initialize(this)
        AppUtil.getInstance().application = this
    }

}

