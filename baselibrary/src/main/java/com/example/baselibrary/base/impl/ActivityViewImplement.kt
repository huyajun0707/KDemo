package com.example.baselibrary.base.impl

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.baselibrary.base.BaseActivity
import com.example.baselibrary.viewmodel.BaseViewModel
import com.example.component.network.adapter.ViewModelFactory
import com.example.component.network.listener.ILoadingView
import com.example.component.utils.LogUtil
import com.example.component.utils.ReflectionUtils
import com.example.component.widget.dialog.ProgressLoadingDialog

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-10-31 14:50
 * @depiction   ：
 */


abstract class ActivityViewImplement<V : ViewDataBinding, T : BaseViewModel<*>> : BaseActivity(),
    ILoadingView {


    protected lateinit var mBinding: V
    protected lateinit var mViewModel: T
    private var dialogFragment: DialogFragment? = null
    private var viewModelId: Int = 0


    override fun initView(savedInstanceState: Bundle?) {
        initViewDataBinding()
        initViewObservable()
    }


    /**
     * 注入绑定
     */
    private fun initViewDataBinding() {
        //DataBindingUtil类需要在project的build中配置 dataBinding {enabled true }, 同步后会自动关联android.databinding包
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModelId = initVariableId()
        mViewModel = initViewModel()
        //关联ViewModel
        mBinding.setVariable(viewModelId, mViewModel)
    }

//    abstract fun initViewModel(): T

    private fun initViewModel(): T {
        return VMProviders(this,ReflectionUtils.getInstance(this, 1) as Class<T>)
    }


    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    abstract fun initVariableId(): Int

    protected fun <T : ViewModel> VMProviders(fragment: AppCompatActivity, @NonNull modelClass: Class<T>): T {
        return ViewModelProviders.of(fragment,
            ViewModelFactory(application, this, this)
        ).get(modelClass)
    }

    abstract fun initViewObservable()


    override fun showLoading() {
        //是否判断在前台
        dialogFragment?.dismiss()
        if (dialogFragment == null) {
            dialogFragment = ProgressLoadingDialog()
        }
        dialogFragment?.show(supportFragmentManager, "progressLoadingDialog")
    }

    override fun hideLoading() {
        dialogFragment?.dismiss()
    }

    override fun showEmpty(msg: String?) {
        LogUtil.d("BaseActivity", "showEmpty")
    }

    override fun showToast(msg: String) {
        LogUtil.d("BaseActivity", "showToast")
    }


    override fun showErrorMessage(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

    override fun showFailureMessage(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }


}
