package com.example.kdemo.ui

import com.example.baselibrary.base.impl.ActivityViewImplement
import com.example.kdemo.BR
import com.example.kdemo.R
import com.example.kdemo.databinding.ActivityLoginBinding
import com.example.kdemo.ui.viewmodel.LoginVM


class LoginActivity : ActivityViewImplement<ActivityLoginBinding, LoginVM>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }


    override fun initViewObservable() {



    }



}
