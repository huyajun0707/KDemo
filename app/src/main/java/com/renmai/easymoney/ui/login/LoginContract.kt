package com.renmai.easymoney.ui.login

import com.renmai.baselibrary.base.mvp.mvpinterface.BaseView
import com.renmai.baselibrary.base.mvp.mvpinterface.IPresenter


/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-11-18 20:55
 * @depiction   ：
 */
interface LoginContract {

    interface View : BaseView {
        fun loginSuccess()

    }

    interface Presenter : IPresenter {
        fun login(mobile: String)
    }


}