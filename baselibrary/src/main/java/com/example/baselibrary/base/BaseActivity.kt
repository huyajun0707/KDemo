package com.example.baselibrary.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.component.network.listener.ILoadingView

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-10-31 14:36
 * @depiction   ：
 */

 abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //状态栏
        initStatusBar()
        //设置布局内容
        setContentView(getLayoutId())

        //初始化控件
        initView(savedInstanceState)
        //初始化ToolBar
        initToolBar()
    }

    abstract fun initView(savedInstanceState: Bundle?)


    abstract fun getLayoutId(): Int

    fun initStatusBar() {
    }


    fun initToolBar() {
    }


}