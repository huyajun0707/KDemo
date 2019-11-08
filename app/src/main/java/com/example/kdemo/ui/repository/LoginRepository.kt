package com.example.kdemo.ui.repository

import androidx.lifecycle.LifecycleOwner
import com.dakai.android.network.domain.BaseResponse
import com.dakai.android.network.observer.RxObserver
import com.example.component.network.listener.DataCallback
import com.example.component.network.listener.HttpCallBack
import com.example.component.utils.bindLifecycleWithScheduler
import com.example.kdemo.entity.IndexStatus
import com.example.kdemo.entity.LoginInfo
import com.example.kdemo.ui.repository.base.BaseRepositoryImpl

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-11-06 16:44
 * @depiction   ：
 */


class LoginRepository : BaseRepositoryImpl() {

//    fun getIndexStatus(owner: LifecycleOwner, dataCallback: DataCallback<IndexStatus>) {
//        apiService.testNetwork().bindLifecycleWithScheduler(owner)
//            .subscribe(RxObserver(HttpCallBack<BaseResponse<IndexStatus>, IndexStatus>(dataCallback)))
//
//    }

    fun login(owner: LifecycleOwner, dataCallback: DataCallback<LoginInfo>, phoneNum: String) {
        apiService.login(phoneNum).bindLifecycleWithScheduler(owner)
            .subscribe(RxObserver(HttpCallBack<BaseResponse<LoginInfo>, LoginInfo>(dataCallback)))

    }


}