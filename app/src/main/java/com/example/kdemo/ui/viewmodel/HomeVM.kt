package com.example.kdemo.ui.viewmodel

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-10-30 17:01
 * @depiction   ：
 */

//class HomeVM(application: Application) : BaseViewModel(application) {
//    val refreshTrigger = MutableLiveData<Boolean>()
//    val api = ApiService.getApi()

//    var baseResponse: LiveData<BaseResponse<IndexStatus>> =
//        Transformations.switchMap(refreshTrigger) {
//            api.testNetwork()
//        }


//    var indexStatus: LiveData<IndexStatus> = MutableLiveData<IndexStatus>()
//
//    fun loadData() {
//        println("--->loaddata")
//        indexStatus = request { api.testNetwork() }
//
//    }


//}