package com.dakai.android.network.interceptor

import android.util.Log
import com.dakai.android.network.cache.CacheInterface
import com.dakai.android.network.cache.CacheMode
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.nio.charset.Charset
import java.util.*

/**
 * @author      ： CuiYancey <cuiyuancheng0322@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-08-14 15:16
 */
class CacheNetworkInterceptor : Interceptor, CacheInterface {
    constructor()
    constructor(cacheMode: CacheMode) {
        this.cacheMode = cacheMode
    }

    /**有网时的缓存过期时间，默认为30s*/
    override var networkCacheTime: Int = 30
    /**无网时的缓存过期时间，默认为30天*/
    override var freeCacheTime: Int = 60 * 60 * 24 * 30
    override var isFileCache: Boolean = false
    override var cacheMode: CacheMode = CacheMode.NO_CACHE

    override fun intercept(chain: Interceptor.Chain): Response {
        when (cacheMode) {
            CacheMode.IF_CACHE_EXPIRED_REQUEST -> {
                return   chain.proceed(chain.request()).newBuilder()
                        .removeHeader("Pragma")
                        //添加缓存配置(设置此缓存在多长时间后过期重新请求接口)，缓存拦截器会根据headers中的缓存信息来判断要不要使用此缓存
                        .addHeader("Cache-Control", "max-age=$networkCacheTime")
                        //.addHeader("Content-Length","test")
                        .build()

            }
            CacheMode.NO_CACHE -> {
                return chain.proceed(chain.request())
            }
        }
    }


}