package com.hyj.kdemo.ui.main.fragment

import com.renmai.baselibrary.base.mvp.presenter.BasePresenter


class BorrowPresenter(val view: BorrowContract.View) : BasePresenter<BorrowContract.View>(view),
    BorrowContract.Presenter {


    override fun destroyThing() {

    }


}
