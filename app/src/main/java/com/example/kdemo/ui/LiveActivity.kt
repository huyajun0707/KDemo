//package com.example.kdemo.ui
//
//import android.os.Bundle
//import android.view.View
//import com.dakai.android.network.ApiService
//import com.dakai.android.network.domain.BaseResponse
//import com.dakai.android.network.listener.HttpOnNextListener
//import com.dakai.android.network.observer.RxObserver
//import com.example.baselibrary.base.ActivityViewImplement
//import com.example.component.utils.bindLifecycleWithScheduler
//import com.example.kdemo.BR
//import com.example.kdemo.R
//import com.example.kdemo.databinding.ActivityLoginBinding
//import com.example.kdemo.entity.IndexStatus
//import com.example.kdemo.ui.viewmodel.HomeVM
//import kotlinx.android.synthetic.main.activity_live.*
//
///**
// * @author      ： HuYajun <huyajun0707@gmail.com>
// * @version     ： 1.0
// * @date        ： 2019-10-31 15:40
// * @depiction   ：
// */
//
//class LiveActivity : ActivityViewImplement<ActivityLoginBinding, HomeVM>() {
//    override fun initVariableId(): Int {
//        return BR.viewModel
//    }
//
//    override fun getLayoutId(): Int {
//        return R.layout.activity_live
//    }
//
//
//    override fun initView(savedInstanceState: Bundle?) {
//        super.initView(savedInstanceState)
//
//    }
//
//    override fun initViewObservable() {
//        button2.setOnClickListener(View.OnClickListener {
//            ApiService.getApi().testNetwork()
//                .bindLifecycleWithScheduler(this)
//                .subscribe(RxObserver(object : HttpOnNextListener<BaseResponse<IndexStatus>>() {
//                    override fun onNext(baseData: BaseResponse<IndexStatus>?) {
//                        println("---->onNext${baseData!!.data.toString()}")
////                        Toast.makeText(this@LiveActivity,"成功",Toast.LENGTH_SHORT).show()
//                        tvGet.setText(baseData!!.data.toString())
//                    }
//                }, this))
//
////            var dialog :ProgressLoadingDialog = ProgressLoadingDialog()
////
////            dialog.show(this@LiveActivity.supportFragmentManager,"dialog")
//
//
//        })
//
//
//    }
//
//
//}
//
