package com.hyj.kdemo.ui.me

import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import com.example.baselibrary.base.impl.ActivityViewImplement
import com.hyj.kdemo.BR
import com.hyj.kdemo.R
import com.hyj.kdemo.databinding.ActivitySettingBinding
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : ActivityViewImplement<ActivitySettingBinding, SettingViewModel>() {
    override fun initToolbar() {


    }

    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initListener() {

    }


    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initialize(savedInstanceState: Bundle?) {
        btTest.setOnClickListener(View.OnClickListener {
            mViewModel.getString()


        })
        mViewModel.value.observe(this, object : Observer<String> {
            override fun onChanged(@Nullable newName: String) {
                // Update the UI, in this case, a TextView.
                println("thread:${Thread.currentThread().name}")
                tvResult.setText(newName)
            }
        })

    }


}
