package com.renmai.baselibrary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-10-31 15:17
 * @depiction   ：
 */
 abstract class BaseFragment: Fragment(){
    private lateinit var rootView: View
    protected var mIsFirstVisible = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(getLayoutResId(), null, false)
        initView(savedInstanceState)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isVis = isHidden || userVisibleHint
        if (isVis && mIsFirstVisible) {
            lazyLoad()
            mIsFirstVisible = false
        }
    }

    /**
     * @return
     */
    abstract fun getLayoutResId(): Int



    /**
     * 初始化views
     *
     * @param state
     */
    abstract fun initView(state: Bundle?)

    /**
     *
     */
    protected abstract fun onStateRefresh()


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            onVisible()
        } else {
            onInVisible()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            onVisible()
        } else {
            onInVisible()
        }
    }

    /**
     * 当界面可见时的操作
     */
    protected fun onVisible() {
        if (mIsFirstVisible && isResumed) {
            lazyLoad()
            mIsFirstVisible = false
        }
    }

    /**
     * 数据懒加载
     */
    protected fun lazyLoad() {

    }

    /**
     * 当界面不可见时的操作
     */
    protected fun onInVisible() {

    }



}