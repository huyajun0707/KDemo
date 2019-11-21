//package com.renmai.baselibrary.base.impl
//
//import android.os.Bundle
//import androidx.annotation.NonNull
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProviders
//import com.renmai.baselibrary.base.BaseFragment
//import com.renmai.baselibrary.viewmodel.BaseViewModel
//import com.renmai.component.utils.ReflectionUtils
//
///**
// * @author      ： HuYajun <huyajun0707@gmail.com>
// * @version     ： 1.0
// * @date        ： 2019-10-31 15:29
// * @depiction   ：
// */
//
// abstract class FragmentViewImplement<T : BaseViewModel<*>> : BaseFragment() {
//    protected var mViewModel: T? = null
//
//
//    override fun initView(state: Bundle?) {
//        mViewModel = VMProviders(this, ReflectionUtils.getInstance(this, 0) as Class<T>)
//        if (null != mViewModel) {
//            dataObserver()
//        }
//    }
//    protected fun <T : ViewModel> VMProviders(fragment: BaseFragment, @NonNull modelClass: Class<T>): T {
//        return ViewModelProviders.of(fragment).get(modelClass)
//
//    }
//
//    abstract fun dataObserver()
//
//
//
//
//
//}