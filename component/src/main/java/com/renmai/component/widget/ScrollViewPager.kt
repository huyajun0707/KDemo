package com.renmai.component.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-11-19 19:20
 * @depiction   ： 可设置是否滑动的ViewPager
 */

class ScrollViewPager : ViewPager {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    private var scrollable = false

    fun setScrollable(isScrollable: Boolean) {
        scrollable = isScrollable
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return scrollable
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return scrollable
    }

}