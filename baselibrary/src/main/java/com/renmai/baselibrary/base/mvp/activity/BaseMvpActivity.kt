package com.renmai.baselibrary.base.mvp.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Lifecycle
import com.gyf.barlibrary.ImmersionBar
import com.renmai.baselibrary.R
import com.renmai.baselibrary.base.BaseActivity
import com.renmai.baselibrary.base.mvp.mvpinterface.BaseView
import com.renmai.baselibrary.base.mvp.mvpinterface.IPresenter
import com.renmai.component.utils.MToast
import com.renmai.component.widget.dialog.ProgressLoadingDialog

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-11-18 10:51
 * @depiction   ：
 */
abstract class BaseMvpActivity<V : ViewDataBinding, P : IPresenter> : BaseActivity(), BaseView,
    View.OnClickListener {

    protected var mPresenter: P? = null
    protected lateinit var mBinding: V
    private var dialogFragment: DialogFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        //初始化presenter
        mPresenter = getPresenter()
        mPresenter?.addLifecycleObserver(lifecycle)
        initBarStatus()
        initViewDataBinding()
        initToolbar()
        initView()
        initListener()
        initData()
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

    }

    override fun onDestroy() {
        super.onDestroy()
        destroyViewAndThing()
        mPresenter = null
    }

    protected abstract fun getPresenter(): P

    /**添加生命周期观察者（未用到）*/
    private fun initLifecycleObserver(lifecycle: Lifecycle) {
        mPresenter?.let {
            lifecycle.addObserver(it)
        }
    }


    /**获取layout资源id*/
    abstract fun getLayoutId(): Int

    /**初始化toolbar*/
    abstract fun initToolbar()

    /**初始化view*/
    abstract fun initView()

    /**初始化事件监听*/
    abstract fun initListener()

    /**初始化数据*/
    abstract fun initData()

    /*执行销毁动作**/
    protected abstract fun destroyViewAndThing()

    override fun showLoading() {
        println("---->showLoading")
        //是否判断在前台
        dialogFragment?.dismiss()
        if (dialogFragment == null) {
            dialogFragment = ProgressLoadingDialog()
        }
        dialogFragment?.show(supportFragmentManager, "progressLoadingDialog")
    }

    override fun hideLoading() {
        println("---->hideLoading")
        dialogFragment?.dismiss()
    }

    override fun showEmpty(msg: String?) {

    }

    override fun showToast(msg: String) {
        MToast.showNormal(msg)
    }

    override fun showFailureMessage(msg: String) {
        MToast.showNormal(msg)
    }

    override fun showErrorMessage(msg: String) {
       MToast.showError(msg)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mImLeftIcon -> finish()
        }
        normalOnClick(v)

    }

    override fun finishActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    abstract fun normalOnClick(v: View?)

}