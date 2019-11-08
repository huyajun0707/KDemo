package com.example.baselibrary.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.baselibrary.repository.BaseRepository
import com.example.component.network.listener.ILoadingView
import com.example.component.utils.ReflectionUtils

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-10-31 14:34
 * @depiction   ：
 */


open class BaseViewModel<T: BaseRepository>(application: Application, val owner: LifecycleOwner, val iLoadingView: ILoadingView) : AndroidViewModel(application) {

    var mRepository: T =  ReflectionUtils.getNewInstance(this, 0)






}