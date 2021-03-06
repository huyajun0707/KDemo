package com.renmai.easymoney.ui.login

import com.renmai.baselibrary.base.mvp.presenter.BasePresenter
import com.renmai.easymoney.net.ApiService
import com.renmai.easymoney.net.DataLoadingCallback
import com.renmai.easymoney.net.callback

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-11-19 09:53
 * @depiction   ：
 */
class LoginPresenter(val view: LoginContract.View) : BasePresenter<LoginContract.View>(view),
    LoginContract.Presenter {
    override fun destroyThing() {

    }


    override fun login(mobile: String) {
//        mWeakOwner?.get()?.let {
//            ApiService.instance.login(mobile)
//                .callback(it, object : DataLoadingCallback<LoginInfo>(view) {
//                    override fun onLoadedData(data: LoginInfo?) {
//                        view.loginSuccess()
//                    }
//
//                })
//
//
//        }

        mWeakOwner?.get()?.let {
            ApiService.instance.testNetwork()
                .callback(it, object : DataLoadingCallback<Any>(view) {
                    override fun onLoadedData(data: Any?) {
                        println("--->success")
                    }

                })


        }

    }


}
