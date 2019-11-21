package com.renmai.easymoney.ui.main.fragment

import android.view.View
import com.renmai.baselibrary.base.mvp.fragment.BaseMvpFragment
import com.renmai.easymoney.R
import com.renmai.easymoney.databinding.FragmentHomeBinding

class HomeFragment : BaseMvpFragment<FragmentHomeBinding, HomeContract.Presenter>(),
    HomeContract.View {

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun getPresenter(): HomeContract.Presenter {
        return HomePresenter(this)
    }


    override fun initView() {

    }

    override fun initData() {
        println("--->initData")
    }

    override fun initListener() {

    }


    override fun normalOnClick(v: View?) {

    }

    override fun destroyViewAndThing() {

    }
}
