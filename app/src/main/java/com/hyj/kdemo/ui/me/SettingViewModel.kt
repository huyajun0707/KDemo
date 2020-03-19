package com.hyj.kdemo.ui.me

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hyj.kdemo.net.ApiService
import com.hyj.kdemo.net.safeLoaddingLaunch
import com.network.library.listener.ILoadingView
import com.renmai.baselibrary.base.viewmodel.BaseViewModel

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2020-01-10 11:34
 * @depiction   ：
 */
class SettingViewModel(
    val application: Application,
    val lifecycleOwner: LifecycleOwner,
    val iLoadingView: ILoadingView
) :
    BaseViewModel<SettingRepository>() {
    var value = MutableLiveData<String>()

    fun getString() {

        viewModelScope.safeLoaddingLaunch(iLoadingView) {
            value.value= ApiService.instance.getIndexStatus().data.toString()
        }

//        viewModelScope.launch {
//           value.value= ApiService.instance.getIndexStatus().data.toString()
//
//        }
    }


}