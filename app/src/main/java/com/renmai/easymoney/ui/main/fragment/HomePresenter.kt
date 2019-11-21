package com.renmai.easymoney.ui.main.fragment

import com.renmai.baselibrary.base.mvp.presenter.BasePresenter


class HomePresenter(val view: HomeContract.View) : BasePresenter<HomeContract.View>(view),
    HomeContract.Presenter {


    override fun destroyThing() {

    }


}
