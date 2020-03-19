package com.hyj.kdemo.ui.main.fragment

import android.view.View
import com.hyj.kdemo.R
import com.hyj.kdemo.databinding.FragmentBorrowBinding
import com.renmai.baselibrary.base.mvp.fragment.BaseMvpFragment

class BorrowFragment : BaseMvpFragment<FragmentBorrowBinding, BorrowContract.Presenter>(),
    BorrowContract.View {

    override fun getLayoutId(): Int {
        return R.layout.fragment_borrow
    }

    override fun getPresenter(): BorrowContract.Presenter {
        return BorrowPresenter(this)
    }


    override fun initView() {

    }

    override fun initData() {
    }

    override fun initListener() {

    }


    override fun normalOnClick(v: View?) {

    }

    override fun destroyViewAndThing() {

    }
}
