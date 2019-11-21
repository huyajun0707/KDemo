package com.github.netlibrary.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.github.netlibrary.listener.DefaultDataCallback
import com.github.netlibrary.listener.HttpCallBack
import com.github.netlibrary.observer.RxObserver
import com.github.netlibrary.retry.RetryWhenNetworkException1
import com.github.netlibrary.retry.RetryWhenNetworkException2
import com.renmai.component.network.BaseResponse
import com.uber.autodispose.FlowableSubscribeProxy
import com.uber.autodispose.ObservableSubscribeProxy
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDisposable
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 网络请求绑定声明周期，并带有失败重试机制。
 * 函数bindLifecycleForNetwork是抽象类Observable的扩展函数，
 * 参考资料：https://yuzhiqiang.blog.csdn.net/article/details/88233525
 */
fun <T> Observable<T>.bindLifecycleForNetwork(owner: LifecycleOwner): ObservableSubscribeProxy<T> {
    return this.compose(RxUtils.io2Main())//线程调度
        .retryWhen(RetryWhenNetworkException1())
        //使用AutoDispose管理生命周期
        .bindLifecycle(owner)
}

/**
 * 适用于背压的网络请求绑定声明周期，并带有失败重试机制。
 * 函数bindLifecycleForNetwork是抽象类Flowable的扩展函数
 */
fun <T> Flowable<T>.bindLifecycleForNetwork(owner: LifecycleOwner): FlowableSubscribeProxy<T> {
    return this.compose(RxUtils.flowIo2Main())//线程调度
        .retryWhen(RetryWhenNetworkException2())
        //使用AutoDispose管理生命周期
        .bindLifecycle(owner)
}

/**
 * 封装线程调度和生命周期绑定（使用AutoDispose）。
 * 函数bindLifecycleWithScheduler是抽象类Observable的扩展函数，
 * 参考资料：https://yuzhiqiang.blog.csdn.net/article/details/88233525
 */
fun <T> Observable<T>.bindLifecycleWithScheduler(owner: LifecycleOwner): ObservableSubscribeProxy<T> {
    return this.compose(RxUtils.io2Main())//线程调度
        //使用AutoDispose管理生命周期
        .bindLifecycle(owner)
}

/**
 * 适用于背压的线程调度和生命周期绑定扩展函数。
 * 函数bindLifecycleWithScheduler是抽象类Flowable的扩展函数
 */
fun <T> Flowable<T>.bindLifecycleWithScheduler(owner: LifecycleOwner): FlowableSubscribeProxy<T> {
    return this.compose(RxUtils.flowIo2Main())//线程调度
        //使用AutoDispose管理生命周期
        .bindLifecycle(owner)
}

/**
 * 封装生命周期绑定。
 * 函数bindLifecycle是抽象类Observable的扩展函数
 */
fun <T> Observable<T>.bindLifecycle(owner: LifecycleOwner): ObservableSubscribeProxy<T> {
    //使用AutoDispose管理生命周期，在onDestroy中解除订阅
    return this.autoDisposable(AndroidLifecycleScopeProvider.from(owner, Lifecycle.Event.ON_DESTROY))
}

/**
 * 适用于背压的生命周期绑定扩展函数。
 * 函数bindLifecycle是抽象类Flowable的扩展函数
 */
fun <T> Flowable<T>.bindLifecycle(owner: LifecycleOwner): FlowableSubscribeProxy<T>{
    //使用AutoDispose管理生命周期，在onDestroy中解除订阅
    return this.autoDisposable(AndroidLifecycleScopeProvider.from(owner, Lifecycle.Event.ON_DESTROY))
}

/**
 * 包装一层减少代码量
 */
fun <T,D> Observable<T>.callback(owner: LifecycleOwner,callback: DefaultDataCallback<D>) {

    return this.bindLifecycleWithScheduler(owner).subscribe(
        RxObserver(
            HttpCallBack<BaseResponse<D>, D>(callback)
        )
    )

}






/**
 * RxJava工具类
 * RxJava的观察者参考https://www.jianshu.com/p/45309538ad94
 * @author      ： CuiYancey <cuiyuancheng0322@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-08-14 10:25
 */
class RxUtils {
    companion object {
        /**
         * Observable的线程调度 io线程切换main线程
         */
        fun <T> io2Main(): ObservableTransformer<T, T> {
            return ObservableTransformer {
                it.subscribeOn(Schedulers.io())                 //订阅线程
                    .unsubscribeOn(Schedulers.io())             //解除订阅线程
                    .observeOn(AndroidSchedulers.mainThread())  //消费事件线程
            }
        }

        /**
         * Flowable的线程调度 io线程切换main线程
         */
        fun <T> flowIo2Main(): FlowableTransformer<T, T>{
            return FlowableTransformer {
                it.subscribeOn(Schedulers.io())                 //订阅线程
                    .unsubscribeOn(Schedulers.io())             //解除订阅线程
                    .observeOn(AndroidSchedulers.mainThread())  //消费事件线程
            }
        }
    }
}