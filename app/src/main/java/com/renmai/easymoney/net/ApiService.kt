package com.renmai.easymoney.net

import com.renmai.baselibrary.base.BaseApplication
import com.renmai.component.network.BaseResponse
import com.renmai.component.utils.UserUtils
import com.renmai.easymoney.BuildConfig
import com.renmai.easymoney.constant.Constant
import com.renmai.easymoney.entity.IndexStatus
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-08-06 23:03
 */
interface ApiService {

    companion object {

        val instance: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            com.github.netlibrary.NetworkEngine.builder().setApplication(BaseApplication.instance())
                .setHeaderMap(mutableMapOf(Pair("Authorization", { UserUtils.getToken() })))
                .setDebug(BuildConfig.DEBUG)
                .baseUrl(Constant.BASE_URL)
                .build()
                .create(ApiService::class.java)
        }
    }

    @GET("api/indexStatus")
    fun testNetwork(): Observable<BaseResponse<IndexStatus>>

    @FormUrlEncoded
    @POST("api/userlogin/v1/sendValidateCode")
    fun login(@Field("mobile") mobile: String): Observable<BaseResponse<*>>


//    @GET
//    abstract operator fun get(@Url url: String, @QueryMap params: Map<String, String>): Observable<BaseResponse<*>>
//
//    //原始数据
//    @POST
//    abstract fun post(@Url url: String, @Body body: RequestBody): Observable<BaseResponse<*>>
}