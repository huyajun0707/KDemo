package com.example.component.network.listener

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-10-29 17:29
 * @depiction   ：
 */

interface ILoadingView {

    /**
     * 显示正在加载进度框
     */
    fun showLoading()

    /**
     * 隐藏正在加载进度框
     */
    fun hideLoading()

    /**
     * 显示提示
     * @param msg
     */
    fun showToast(msg: String)

    /**
     * 当数据为空时，调用此函数
     * [msg]提示信息，可为空
     */
    fun showEmpty(msg: String?)

    /**
     * 当数据请求失败后（接口返回code != 200），调用此接口提示
     * @param msg 失败原因
     */
    fun showFailureMessage(msg: String)

    /**
     * 当数据请求异常，调用此接口提示
     */
    fun showErrorMessage(msg: String)



}