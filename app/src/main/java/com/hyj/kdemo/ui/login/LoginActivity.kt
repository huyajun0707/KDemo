package com.hyj.kdemo.ui.login

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.View
import com.hyj.kdemo.R
import com.hyj.kdemo.databinding.ActivityLoginBinding
import com.hyj.kdemo.ui.main.MainActivity
import com.renmai.baselibrary.base.mvp.activity.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_test.*

class LoginActivity : BaseMvpActivity<ActivityLoginBinding, LoginContract.Presenter>(),
    LoginContract.View {
    override fun setView(bitmap: Bitmap) {
        imageView.setImageBitmap(bitmap)

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_test
    }

    override fun getPresenter(): LoginContract.Presenter {
        return LoginPresenter(this)
    }

    override fun initToolbar() {
//        NavBarHelper.Builder()
//            .builderToolbar(mBinding.getRoot())
//            .builderClickLister(this)
//            .builderLeftIcon(R.drawable.icon_go_back)
//            .builderCenterString("")
//            .createNormal()
    }

    override fun initView() {

    }

    override fun initData() {
    }

    override fun initListener() {
//        mBtNext.setOnClickListener(this)
        btDownload.setOnClickListener(this)
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
    var i = 0

    @SuppressLint("AutoDispose")
    override fun normalOnClick(v: View?) {
//        v?.clicks()?.throttleFirst(2,TimeUnit.SECONDS)?.subscribe(Consumer {
//
//            when (v?.id) {
//                R.id.mBtNext -> {
////                    println("mbtNext${i++}")
//                    mPresenter?.login("111")
//                }
//            }
//        })

        when (v?.id) {
            R.id.btDownload -> {
                mPresenter?.login("111")
            }
        }

    }

    override fun loginSuccess() {
        startActivity(MainActivity::class.java)

    }


    override fun destroyViewAndThing() {

    }

}

