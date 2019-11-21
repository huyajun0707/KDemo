package com.renmai.easymoney.ui.main.fragment

import android.view.View
import com.renmai.baselibrary.base.mvp.fragment.BaseMvpFragment
import com.renmai.easymoney.R
import com.renmai.easymoney.databinding.FragmentMineBinding

class MineFragment : BaseMvpFragment<FragmentMineBinding, MineContract.Presenter>(),
    MineContract.View {

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun getPresenter(): MineContract.Presenter {
        return MinePresenter(this)
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
