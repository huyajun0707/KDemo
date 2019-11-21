package com.renmai.easymoney.ui.main

import android.view.MenuItem
import android.view.View
import com.renmai.baselibrary.base.mvp.activity.BaseMvpActivity
import com.renmai.easymoney.R
import com.renmai.easymoney.databinding.ActivityMainBinding
import com.renmai.easymoney.ui.main.adapter.ViewPagerAdapter
import com.renmai.easymoney.ui.main.fragment.BorrowFragment
import com.renmai.easymoney.ui.main.fragment.HomeFragment
import com.renmai.easymoney.ui.main.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity<ActivityMainBinding, MainContract.Presenter>(),
    MainContract.View {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getPresenter(): MainContract.Presenter {
        return MainPresenter(this)
    }

    override fun initToolbar() {
    }

    override fun initView() {
        viewPager.adapter = ViewPagerAdapter(
            supportFragmentManager,
            listOf(HomeFragment(), BorrowFragment(), MineFragment())
        )
    }

    override fun initData() {
    }

    override fun initListener() {
        navigation.setOnNavigationItemSelectedListener { menuItem ->
            onBottomNavigationSelectChanged(menuItem)
            true
        }
    }


    override fun normalOnClick(v: View?) {

    }

    override fun destroyViewAndThing() {

    }


    private fun onBottomNavigationSelectChanged(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.nav_home -> {
                viewPager.currentItem = 0
            }
            R.id.nav_borrow -> {
                viewPager.currentItem = 1
            }
            R.id.nav_mine -> {
                viewPager.currentItem = 2
            }
        }
    }
}
