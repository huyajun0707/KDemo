package com.dakai.android.network

import android.util.Log
import com.dakai.android.network.domain.BaseResponse
import com.example.kdemo.entity.IndexStatus
import com.example.kdemo.entity.LoginInfo
import retrofit2.http.*
import io.reactivex.*

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-08-06 23:03
 */
interface ApiService {

    companion object {
        private var mApiService: ApiService? = null

        fun getApi(): ApiService {
            //如果ApiService为空或者缓存配置改变，重新初始化mApiService
            if (mApiService == null || NetworkEngine.instance.isCacheConfigureChanged) {
                /**代理机制创建ApiService*/
                mApiService = NetworkEngine.instance.getRetrofitLiveDataConfig().apply {
                    if (NetworkEngine.instance.isCache) {
                        Log.e("cui_", "设置缓存配置")
                        this.client(NetworkEngine.instance.getOkHttpClientCacheConfig().build())
                    } else {
                        Log.e("cui_", "不使用缓存配置")
                        this.client(NetworkEngine.instance.getOkHttpClientConfig().build())
                    }
                }.baseUrl(NetworkEngine.instance.getBaseUrl())
                    .build()
                    .create(ApiService::class.java)
            }
            return mApiService!!
        }
    }

    @GET("api/indexStatus")
    fun testNetwork(): Observable<BaseResponse<IndexStatus>>

    @POST("api//api/userlogin/v1/sendValidateCode")
    fun login(@Field("mobile")mobile:String): Observable<BaseResponse<LoginInfo>>
}