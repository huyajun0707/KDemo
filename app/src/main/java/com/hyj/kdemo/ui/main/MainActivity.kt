package com.hyj.kdemo.ui.main

import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.hyj.kdemo.R
import com.hyj.kdemo.databinding.ActivityMainBinding
import com.hyj.kdemo.ui.main.adapter.ViewPagerAdapter
import com.hyj.kdemo.ui.main.fragment.BorrowFragment
import com.hyj.kdemo.ui.main.fragment.HomeFragment
import com.hyj.kdemo.ui.main.fragment.MineFragment
import com.renmai.baselibrary.base.mvp.activity.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity<ActivityMainBinding, MainContract.Presenter>(),
    MainContract.View {

    var fragmentList = mutableListOf<Fragment>()

    var currentFragment: Fragment? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getPresenter(): MainContract.Presenter {
        return MainPresenter(this)
    }

    override fun initToolbar() {
    }

    override fun initView() {
        fragmentList.add(HomeFragment())
        fragmentList.add(BorrowFragment())
        fragmentList.add(MineFragment())
        viewPager.offscreenPageLimit = 0
        viewPager.adapter = ViewPagerAdapter(
            supportFragmentManager, fragmentList
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
        var index = 0
        when (menuItem.itemId) {
            R.id.nav_home -> {
                index = 0
            }
            R.id.nav_borrow -> {
                index = 1
            }
            R.id.nav_mine -> {
                index = 2
            }
        }
        viewPager.currentItem = index
        currentFragment = fragmentList[index]
    }
}
