package com.renmai.baselibrary.base

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.renmai.baselibrary.base.mvp.mvpinterface.BaseView

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-10-31 14:36
 * @depiction   ：
 */

abstract class BaseActivity : AppCompatActivity(), BaseView{


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)


    }


    /**
     * 系统低内存环境：在系统内存不足，所有后台程序（优先级为background的进程，不是指后台运行的进程）都被杀死时，系统会调用OnLowMemory
     * 参考：https://www.cnblogs.com/sudawei/p/3527145.html
     *      https://www.jianshu.com/p/a5712bdb2dfd
     */
    override fun onLowMemory() {
        super.onLowMemory()
        //清除Glide内存缓存，必须在UI线程中调用
        Glide.get(this).clearMemory()
    }

    /**
     * 应用程序在不同的情况下进行自身的内存释放，以避免被系统直接杀掉，提高应用程序的用户体验
     * 更多OnTrimMemory优化参考：https://www.jianshu.com/p/5b30bae0eb49
     */
    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        when (level) {
            TRIM_MEMORY_UI_HIDDEN, //内存不足，并且该进程的UI已经不可见了
            TRIM_MEMORY_RUNNING_CRITICAL,//内存不足(后台进程不足3个)，并且该进程优先级比较高，需要清理内存
            TRIM_MEMORY_RUNNING_LOW,//内存不足(后台进程不足5个)，并且该进程优先级比较高，需要清理内存
            TRIM_MEMORY_RUNNING_MODERATE//内存不足(后台进程超过5个)，并且该进程优先级比较高，需要清理内存
            -> {
                Glide.get(this).clearMemory()
            }
            else -> {
            }
        }
        Glide.get(this).trimMemory(level)
    }


    open fun startActivity(clazz: Class<*>){
        var intent = Intent(this,clazz)
        startActivity(intent)
    }





}