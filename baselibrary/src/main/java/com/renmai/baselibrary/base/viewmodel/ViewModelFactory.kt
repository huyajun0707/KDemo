package com.github.netlibrary.cache.adapter

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-11-06 11:13
 * @depiction   ：viewModel初始化类
 */

class ViewModelFactory(
    val application: Application,
    val rowner: LifecycleOwner,
    val iLoadingView: com.network.library.listener.ILoadingView
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            Application::class.java,
            LifecycleOwner::class.java,
            com.network.library.listener.ILoadingView::class.java
        ).newInstance(application, rowner, iLoadingView)

    }
//(ReflectionUtils.getInstance(this,0) as Class<D>)::class.java
}