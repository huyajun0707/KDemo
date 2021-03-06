package com.renmai.baselibrary.base.mvp.mvpinterface

import com.github.netlibrary.listener.ILoadingView


/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-11-18 20:50
 * @depiction   ：
 */

interface BaseView : ILoadingView {

    /**
     * 显示提示
     * @param msg
     */
    fun showToast(msg: String)


}