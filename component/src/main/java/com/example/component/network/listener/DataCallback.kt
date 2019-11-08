package com.example.component.network.listener

import com.dakai.android.network.domain.BaseResponse

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-09-24 18:06
 * @Describe    ： 数据回调接口
 */
interface DataCallback<T> {

    /**开始网络请求*/
    fun onStart()

    /**接口返回的BaseResponse<D>数据的处理逻辑*/
    fun onBaseDataHandle(baseData: BaseResponse<T>?)

    /**获取到网络数据[t]，数据可能为null*/
    fun onLoadedData(t: T?)

    /**网络请求失败，非200情况时调用*/
    fun onFailed(msg: String)

    /**网络请求出错*/
    fun onError(e: Throwable)

    /**网络请求结束*/
    fun onComplete()
}

/**
 * 通用的数据回调抽象类，
 * [D]为数据类型
 * [V]为view接口
 * TODO 注意：如果有特殊需求，请自行实现[DataCallback]接口
 *
 * @author      ： CuiYancey <cuiyuancheng0322@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-09-25 11:18
 */
abstract class DefaultDataCallback<D>(view: ILoadingView?) :
    DataCallback<D> {
    private var mView: ILoadingView? = null

    init {
        this.mView = view
    }

    override fun onStart() {
        mView?.showLoading()
    }

    override fun onFailed(msg: String) {
        mView?.showFailureMessage(msg)
    }

    override fun onError(e: Throwable) {
        mView?.showErrorMessage(e.message.toString())
        mView?.hideLoading()
    }

    override fun onComplete() {
        mView?.hideLoading()
    }


    /**接口返回的BaseResponse<D>数据的处理逻辑*/
    override fun onBaseDataHandle(baseData: BaseResponse<D>?) {
        if (baseData != null) {
            if (baseData.code.equals("200")) {
                //展示数据
                onLoadedData(baseData.data)
            } else {
                onFailed(baseData.msg)
            }
        } else {
            //BaseResponse为空，显示获取数据失败视图
            onFailed("未获取到网络数据。")
        }
    }

}