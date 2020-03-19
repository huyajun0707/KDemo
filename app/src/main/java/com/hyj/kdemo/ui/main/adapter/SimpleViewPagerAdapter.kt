package com.hyj.kdemo.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(
    fragmentManager: FragmentManager, private val fragments: List<Fragment>
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

//    private var mCurrentPrimaryItem :Fragment ?= null


    override fun getItem(index: Int): Fragment = fragments[index]

    override fun getCount(): Int = fragments.size

}