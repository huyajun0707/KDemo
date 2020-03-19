package com.hyj.kdemo.ui.main.fragment

import android.view.View
import com.hyj.kdemo.R
import com.hyj.kdemo.databinding.FragmentMineBinding
import com.renmai.baselibrary.base.mvp.fragment.BaseMvpFragment

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
