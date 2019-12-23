package com.renmai.easymoney.ui.login

import android.annotation.SuppressLint
import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import com.renmai.baselibrary.base.mvp.activity.BaseMvpActivity
import com.renmai.easymoney.R
import com.renmai.easymoney.databinding.ActivityLoginBinding
import com.renmai.easymoney.helper.NavBarHelper
import com.renmai.easymoney.ui.main.MainActivity
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.TimeUnit

class LoginActivity : BaseMvpActivity<ActivityLoginBinding, LoginContract.Presenter>(),
    LoginContract.View {

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getPresenter(): LoginContract.Presenter {
        return LoginPresenter(this)
    }

    override fun initToolbar() {
        NavBarHelper.Builder()
            .builderToolbar(mBinding.getRoot())
            .builderClickLister(this)
            .builderLeftIcon(R.drawable.icon_go_back)
            .builderCenterString("")
            .createNormal()
    }

    override fun initView() {

    }

    override fun initData() {
    }

    override fun initListener() {
        mBtNext.setOnClickListener(this)
    }

    /**
     * @param ctx
     * @param originalSource
     * @param gifDisplayTime
     * @param hasGifDiskCacheStrategy
     * @param placeHolderDrawable
     * @param errorDrawable
     * @param strategy
     * @param baseTarget
     */
    var i =0
    @SuppressLint("AutoDispose")
    override fun normalOnClick(v: View?) {
        v?.clicks()?.throttleFirst(2,TimeUnit.SECONDS)?.subscribe(Consumer {

            when (v?.id) {
                R.id.mBtNext -> {
//                    println("mbtNext${i++}")
                    mPresenter?.login("111")
                }
            }
        })


    }

    override fun loginSuccess() {
        startActivity(MainActivity::class.java)

    }


    override fun destroyViewAndThing() {

    }

}

