package com.hyj.kdemo.ui.main.fragment

import android.view.View
import com.hyj.kdemo.R
import com.hyj.kdemo.databinding.FragmentHomeBinding
import com.renmai.baselibrary.base.mvp.fragment.BaseMvpFragment

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
