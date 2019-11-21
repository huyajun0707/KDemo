package com.renmai.easymoney.ui.main.fragment

import android.view.View
import com.renmai.baselibrary.base.mvp.fragment.BaseMvpFragment
import com.renmai.easymoney.R
import com.renmai.easymoney.databinding.FragmentBorrowBinding

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
