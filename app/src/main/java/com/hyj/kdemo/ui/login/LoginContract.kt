package com.hyj.kdemo.ui.login

import android.graphics.Bitmap
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

        fun setView(bitmap: Bitmap)

    }

    interface Presenter : IPresenter {
        fun login(mobile: String)

        fun download()


    }


}