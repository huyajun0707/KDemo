package com.hyj.kdemo.net

import androidx.lifecycle.LifecycleOwner
import com.network.library.listener.DataCallback
import com.network.library.utils.bindLifecycleForNetwork
import com.network.library.utils.bindLifecycleWithScheduler
import com.renmai.component.network.BaseResponse
import io.reactivex.Observable

/**
 * 通用的数据回调抽象类，
 * [D]为数据类型
 * [V]为view接口
 * TODO 注意：如果有特殊需求，请自行实现[DataCallback]接口
 *
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-09-25 11:18
 */
abstract class DataLoadingCallback<D>(view: com.network.library.listener.ILoadingView?) :
    com.network.library.listener.DataCallback<D> {
    private var mView: com.network.library.listener.ILoadingView? = null

    init {
        this.mView = view
    }

    override fun onStart() {
        mView?.showLoading()
    }

    override fun onFailed(code: String, msg: String) {
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
                onFailed(baseData.code!!, baseData.msg!!)
            }
        } else {
            //BaseResponse为空，显示获取数据失败视图
            onFailed("99999", "未获取到网络数据。")
        }
    }

}

/**
 * 没有loading窗
 */
abstract class DataNoLoadingCallback<D>(view: com.network.library.listener.ILoadingView?) :
    com.network.library.listener.DataCallback<D> {
    private var mView: com.network.library.listener.ILoadingView? = null

    init {
        this.mView = view
    }

    override fun onStart() {
        mView?.showLoading()
    }

    override fun onFailed(code: String, msg: String) {
        mView?.showFailureMessage(msg)
    }

    override fun onError(e: Throwable) {
        mView?.showErrorMessage(e.message.toString())
    }

    override fun onComplete() {
        mView?.hideLoading()
    }


    /**接口返回的BaseResponse<D>数据的处理逻辑*/
    override fun onBaseDataHandle(baseData: BaseResponse<D>?) {
        if (baseData != null) {
            if (baseData.code.equals("200") && baseData.success!!) {
                //展示数据
                onLoadedData(baseData.data)
            } else {
                onFailed(baseData.code!!, baseData.msg!!)
            }
        } else {
            //BaseResponse为空，显示获取数据失败视图
            onFailed("99999", "未获取到网络数据。")
        }
    }

}


/**
 * 包装一层减少代码量
 */
fun <T, D> Observable<T>.callback(owner: LifecycleOwner, callback: DataLoadingCallback<D>) {

    return this.bindLifecycleWithScheduler(owner).subscribe(
        com.network.library.observer.RxObserver(
            com.network.library.listener.HttpCallBack<BaseResponse<D>, D>(callback)
        )
    )

}

/**
 * 包装一层减少代码量
 */
fun <T, D> Observable<T>.callback(owner: LifecycleOwner, callback: DataNoLoadingCallback<D>) {

    return this.bindLifecycleWithScheduler(owner).subscribe(
        com.network.library.observer.RxObserver(
            com.network.library.listener.HttpCallBack<BaseResponse<D>, D>(callback)
        )
    )

}

fun <T, D> Observable<T>.retryCallback(owner: LifecycleOwner, callback: DataLoadingCallback<D>) {

    return this.bindLifecycleForNetwork(owner).subscribe(
        com.network.library.observer.RxObserver(
            com.network.library.listener.HttpCallBack<BaseResponse<D>, D>(callback)
        )
    )
}


fun <T, D> Observable<T>.retryCallback(owner: LifecycleOwner, callback: DataNoLoadingCallback<D>) {

    return this.bindLifecycleForNetwork(owner).subscribe(
        com.network.library.observer.RxObserver(
            com.network.library.listener.HttpCallBack<BaseResponse<D>, D>(callback)
        )
    )
}