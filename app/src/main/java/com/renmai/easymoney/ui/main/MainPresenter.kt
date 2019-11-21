package com.renmai.easymoney.ui.main

import com.renmai.baselibrary.base.mvp.presenter.BasePresenter


class MainPresenter(val view: MainContract.View) : BasePresenter<MainContract.View>(view),
    MainContract.Presenter {

    override fun destroyThing() {

    }


}
