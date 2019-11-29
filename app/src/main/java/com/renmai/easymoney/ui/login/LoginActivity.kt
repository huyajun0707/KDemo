package com.renmai.easymoney.ui.login

import android.view.View
import com.renmai.baselibrary.base.mvp.activity.BaseMvpActivity
import com.renmai.easymoney.R
import com.renmai.easymoney.databinding.ActivityLoginBinding
import com.renmai.easymoney.helper.NavBarHelper
import com.renmai.easymoney.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

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
    override fun normalOnClick(v: View?) {

        when (v?.id) {
            R.id.mBtNext -> {
                mPresenter?.login(mEtPhoneNum.text.toString())
            }
        }

    }

    override fun loginSuccess() {
        startActivity(MainActivity::class.java)

    }


    override fun destroyViewAndThing() {

    }

}

