package com.hyj.kdemo.net

import com.hyj.kdemo.BuildConfig
import com.hyj.kdemo.entity.IndexStatus
import com.renmai.baselibrary.base.BaseApplication
import com.renmai.component.network.BaseResponse
import com.renmai.component.utils.UserUtils
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-08-06 23:03
 */
interface ApiService {

    companion object {

        val instance: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            com.network.library.NetworkEngine.builder().setApplication(BaseApplication.instance())
                .setHeaderMap(mutableMapOf(Pair("Authorization", { UserUtils.getToken() })))
                .setDebug(BuildConfig.DEBUG)
                .baseUrl(com.hyj.kdemo.constant.Constant.BASE_URL)
                .build()
                .create(com.hyj.kdemo.net.ApiService::class.java)
        }
    }

//    @GET("api/indexStatus")
//    fun testNetwork(): Observable<BaseResponse<Any>>
//
//    @FormUrlEncoded
//    @POST("api/userlogin/v1/sendValidateCode")
//    fun login(@Field("mobile") mobile: String): Observable<BaseResponse<*>>
//
//    @GET("api/v1/user/info")
//    fun getUserInfo(): Observable<BaseResponse<UserInfo>>


    @GET("api/indexStatus")
    suspend fun getIndexStatus(): BaseResponse<IndexStatus>



    @GET("api/indexStatus")
    fun test(): Call<BaseResponse<IndexStatus>>

    //"it/u=2080745074,2793209448&fm=26&gp=0.jpg"
//    @Streaming
//    @GET
//    suspend fun download(@Url url: String): ResponseBody

//    @GET
//    abstract operator fun get(@Url url: String, @QueryMap params: Map<String, String>): Observable<BaseResponse<*>>
//
//    //原始数据
//    @POST
//    abstract fun post(@Url url: String, @Body body: RequestBody): Observable<BaseResponse<*>>
}