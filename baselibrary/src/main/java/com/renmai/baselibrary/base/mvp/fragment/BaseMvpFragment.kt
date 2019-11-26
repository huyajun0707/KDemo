package com.renmai.baselibrary.base.mvp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.renmai.baselibrary.base.mvp.mvpinterface.BaseView
import com.renmai.baselibrary.base.mvp.mvpinterface.IPresenter
import org.jetbrains.annotations.NotNull

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-11-18 10:51
 * @depiction   ：
 */
abstract class BaseMvpFragment<V : ViewDataBinding, P : IPresenter> : Fragment(), BaseView,
    View.OnClickListener {

    protected var mPresenter: P? = null

    protected lateinit var mBinding: V

    /**当前Fragment对用户是否可见*/
    protected var isVisibleToUser: Boolean = false
    /**当前Fragment视图是否实例化*/
    private var isViewInitialized: Boolean = false
    /**当前Fragment数据是否实例化*/
    private var isDataInitialized: Boolean = false
    /**是否使用懒加载*/
    private var isLazyLoadEnabled = true
    /**是否可以在基类中调用初始化数据（initData()）的方法 */
    private var isInitDataInBase = true

    /**关闭懒加载，在[onAttach]函数调用*/
    protected fun closeLazyLoad() {
        isLazyLoadEnabled = false
    }

    /**设置不能在基类中初始化数据，开发者自己决定数据何时初始化，在[onAttach]函数调用*/
    protected fun unableInitDataInBase() {
        isInitDataInBase = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化presenter
        mPresenter = getPresenter()
        mPresenter?.addLifecycleObserver(lifecycle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (getLayoutId() != 0) {
            mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
            mBinding.root
        } else super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //1、加载视图
        isViewInitialized = true
        initView()
        lifecycle()
        initListener()
        if (isInitDataInBase) {
            //2、可以在基类中加载数据，加载数据
            if (!isLazyLoadEnabled) {
                //2.1需要懒加载
                if (savedInstanceState != null) {
                    onRestoreInstanceState(savedInstanceState)
                }
                if (isDataInitialized) {
                    initData()
                } else {
                    checkHaveLoaded()
                }
            } else {
                //2.2不需要懒加载，直接加载数据
                isDataInitialized = true
                initData()
            }
        }
    }

    private fun onRestoreInstanceState(savedInstanceState: Bundle) {
        isDataInitialized = true
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        checkHaveLoaded()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isViewInitialized = false
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyViewAndThing()
        mPresenter = null
    }

    protected abstract fun getPresenter(): P

    /**获取layout资源id*/
    protected abstract fun getLayoutId(): Int

    /**检查是否已加载数据*/
    private fun checkHaveLoaded() {
        //当对用户可见，并且视图已经实例化，而数据未实例化的时候，实例化数据
        if (isVisibleToUser && isViewInitialized && !isDataInitialized) {
            isDataInitialized = true
            initData()
        }
    }


    abstract fun initView()

    /**初始化事件监听*/
    abstract fun initListener()

    /**初始化数据*/
    abstract fun initData()


    override fun onClick(v: View?) {
        normalOnClick(v)
    }


    /**
     * 不用检查网络，可以直接触发的点击事件
     *
     * @param v
     */
    open fun normalOnClick(v: View?) {

    }


    /*执行销毁动作**/
    protected abstract fun destroyViewAndThing()


    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showToast(msg: String) {

    }

    override fun showEmpty(msg: String?) {

    }

    override fun showFailureMessage(msg: String) {
    }

    override fun showErrorMessage(msg: String) {

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        println("--->${javaClass.name}+：${hidden}")

    }


    fun lifecycle(){
      lifecycle.addObserver(object : LifecycleObserver {

          @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
          fun onResume(@NotNull owner: LifecycleOwner){
              println("--->LifecycleObserver:${getPresenter()}：onResume")
          }

          @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
          fun onPause(@NotNull owner: LifecycleOwner){
              println("--->LifecycleObserver:${javaClass.name}：onPause")
          }


      })


    }



}