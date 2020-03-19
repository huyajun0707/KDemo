package com.hyj.kdemo.ui.main.fragment

import com.renmai.baselibrary.base.mvp.presenter.BasePresenter


class MinePresenter(val view: MineContract.View) : BasePresenter<MineContract.View>(view),
    MineContract.Presenter {


    override fun destroyThing() {

    }


}
