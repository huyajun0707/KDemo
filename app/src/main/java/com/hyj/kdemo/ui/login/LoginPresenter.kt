package com.hyj.kdemo.ui.login

import com.renmai.baselibrary.base.mvp.presenter.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-11-19 09:53
 * @depiction   ：
 */
class LoginPresenter(val view: LoginContract.View) : BasePresenter<LoginContract.View>(view),
    LoginContract.Presenter {
    override fun download() {
        GlobalScope.launch(Dispatchers.Main) {
            //http://img1.gtimg.com/
//           var response =  ApiService.instance.download("rushidao/pics/hv1/20/108/1744/113431160.jpg")
//            val inputStream = response.byteStream()
//            val bitmap = BitmapFactory.decodeStream(inputStream)
//            view.setView(bitmap)


        }
    }

    override fun destroyThing() {

    }


    override fun login(mobile: String) {
//        mWeakOwner?.get()?.let {
//            ApiService.instance.login(mobile)
//                .callback(it, object : DataLoadingCallback<LoginInfo>(view) {
//                    override fun onLoadedData(data: LoginInfo?) {
//                        view.loginSuccess()
//                    }
//
//                })
//
//
//        }

        mWeakOwner?.get()?.let {
            //            ApiService.instance.testNetwork()
//                .callback(it, object : DataLoadingCallback<Any>(view) {
//                    override fun onLoadedData(data: Any?) {
//                        println("--->success")
//                    }
//
//                })


            GlobalScope.launch(Dispatchers.Main) {
                try {
                    var result = com.hyj.kdemo.net.ApiService.instance.getIndexStatus()

//                    withContext(Dispatchers.IO){
//                        deferred.cancel()
//                    }
//                    var indexStatus = deferred.await()
                    println(Thread.currentThread().name + ":${result.data.toString()}")
                } catch (e: Exception) {
                    println("-->${e.message}")
//                    Toast.makeText(BaseApplication.instance(), e.mesage, Toast.LENGTH_SHORT).show()
                }
            }
//            GlobalScope.safeLaunch({ ApiService.instance.getIndexStatus() },
//                object : DataNoLoadingCallback<IndexStatus>(view) {
//                    override fun onLoadedData(data: IndexStatus?) {
//                        println(Thread.currentThread().name + ":${data.toString()}")
//                    }
//                })




        }



    }


}

