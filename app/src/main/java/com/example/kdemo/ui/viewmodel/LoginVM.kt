package com.example.kdemo.ui.viewmodel

import android.app.Application
import android.text.TextUtils
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import com.example.baselibrary.viewmodel.BaseViewModel
import com.example.component.network.listener.DataCallback
import com.example.component.network.listener.DefaultDataCallback
import com.example.component.network.listener.ILoadingView
import com.example.kdemo.entity.IndexStatus
import com.example.kdemo.entity.LoginInfo
import com.example.kdemo.ui.repository.LoginRepository

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-11-05 16:52
 * @depiction   ：
 */
class LoginVM(application: Application, owner: LifecycleOwner, iLoadingView: ILoadingView) :
    BaseViewModel<LoginRepository>(application, owner, iLoadingView) {

    var phoneNum: ObservableField<String> = ObservableField("")


    fun login() {
        if (!TextUtils.isEmpty(phoneNum.get())) {
            mRepository.login(owner,object :DefaultDataCallback<LoginInfo>(iLoadingView){
                override fun onLoadedData(t: LoginInfo?) {

                }

            },phoneNum.get().toString())
        } else {
            Toast.makeText(getApplication(), "请输入姓名", Toast.LENGTH_SHORT).show()
        }

    }

}