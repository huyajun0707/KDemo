package com.renmai.baselibrary.base.mvp.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.renmai.baselibrary.base.mvp.mvpinterface.BaseView
import com.renmai.baselibrary.base.mvp.mvpinterface.IPresenter
import java.lang.ref.WeakReference


/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-11-15 16:05
 * @depiction   ： 
 */
abstract class BasePresenter<V : BaseView>(view: V) : IPresenter {

    protected var mView: V? = null
    protected var mWeakOwner: WeakReference<LifecycleOwner>? = null


    //
    init {
        mView = view
    }

    override fun addLifecycleObserver(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    override fun onCreate(owner: LifecycleOwner) {
        mWeakOwner = WeakReference(owner)
    }

    override fun onStart(owner: LifecycleOwner) {
    }

    override fun onResume(owner: LifecycleOwner) {}

    override fun onPause(owner: LifecycleOwner) {}

    override fun onStop(owner: LifecycleOwner) {}

    override fun onDestroy(owner: LifecycleOwner) {
        destroyThing()
        mView = null
        mWeakOwner?.clear()
        mWeakOwner = null
    }

    override fun onLifecycleChanged(owner: LifecycleOwner, event: Lifecycle.Event) {
    }

    /*执行销毁动作**/
    protected abstract fun destroyThing()

}