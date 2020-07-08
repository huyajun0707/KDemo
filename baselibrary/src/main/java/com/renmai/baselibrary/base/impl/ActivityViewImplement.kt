package com.example.baselibrary.base.impl

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.github.netlibrary.cache.adapter.ViewModelFactory
import com.gyf.barlibrary.ImmersionBar
import com.network.library.utils.LogUtil
import com.renmai.baselibrary.R
import com.renmai.baselibrary.base.BaseActivity
import com.renmai.baselibrary.base.viewmodel.BaseViewModel
import com.renmai.component.utils.ReflectionUtils
import com.renmai.component.widget.dialog.ProgressLoadingDialog

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-10-31 14:50
 * @depiction   ：
 */


abstract class ActivityViewImplement<V : ViewDataBinding, T : BaseViewModel<*>> :
    BaseActivity(), com.network.library.listener.ILoadingView, View.OnClickListener {


    protected lateinit var mBinding: V
    protected lateinit var mViewModel: T
    private var dialogFragment: DialogFragment? = null
    private var viewModelId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        //初始化presenter

        initBarStatus()
        initViewDataBinding()
        initToolbar()
        initView(savedInstanceState)
        initListener()
    }

    /**初始化toolbar*/
    abstract fun initToolbar()


    /**获取layout资源id*/
    abstract fun getLayoutId(): Int

    /**初始化事件监听*/
    abstract fun initListener()


    fun initView(savedInstanceState: Bundle?) {
        initViewDataBinding()
        initialize(savedInstanceState)
    }

    protected fun initBarStatus() {
        ImmersionBar.with(this)//
            .statusBarColor(R.color.color_ffffff)//
            .fitsSystemWindows(true)  //使用该属性必须指定状态栏的颜色，不然状态栏透明，很难看
            .statusBarDarkFont(true, 0.2f)//
            .init()//
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
        return VMProviders(this, ReflectionUtils.getInstance(this, 1) as Class<T>)
    }


    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    abstract fun initVariableId(): Int

    protected fun <T : ViewModel> VMProviders(fragment: AppCompatActivity, @NonNull modelClass: Class<T>): T {
        return ViewModelProviders.of(
            fragment,
            ViewModelFactory(application, this, this)
        ).get(modelClass)
    }

    /**初始化数据*/
    abstract fun initialize(savedInstanceState: Bundle?)


    override fun showLoading() {

        runOnUiThread{
            //是否判断在前台
            dialogFragment?.dismiss()
            if (dialogFragment == null) {
                dialogFragment = ProgressLoadingDialog()
            }
            dialogFragment?.show(supportFragmentManager, "progressLoadingDialog")
        }

    }

    override fun hideLoading() {
        runOnUiThread {
            dialogFragment?.dismiss()
        }
    }

    override fun showEmpty(msg: String?) {
        LogUtil.d("BaseActivity", "showEmpty")
    }

    override fun showToast(msg: String) {
        LogUtil.d("BaseActivity", "showToast")
    }


    override fun showErrorMessage(msg: String) {
        runOnUiThread {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showFailureMessage(msg: String) {
        runOnUiThread {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mImLeftIcon -> finish()
        }
        normalOnClick(v)

    }

    fun normalOnClick(v: View?) {

    }

    override fun finishActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
