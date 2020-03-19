package com.network.library.interceptor

import android.content.Context
import android.util.Log
import com.network.library.utils.NetworkUtil
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit


/**
 * 参考：https://www.cnblogs.com/lenve/p/6063851.html
 * maxAge:没有超出maxAge,不管怎么样都是返回缓存数据，超过了maxAge,发起新的请求获取数据更新，请求失败返回缓存数据。
 * maxStale:没有超过maxStale，不管怎么样都返回缓存数据，超过了maxStale,发起请求获取更新数据，请求失败返回失败
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-08-14 15:44
 */
class CacheInterceptor : Interceptor, com.network.library.cache.CacheInterface {
    constructor()
    constructor(cacheMode: com.network.library.cache.CacheMode, context: Context) {
        this.cacheMode = cacheMode
        this.context = context
    }

    private var context: Context? = null
    /**有网时的缓存过期时间，默认为30s*/
    override var networkCacheTime: Int = 30
    /**无网时的缓存过期时间，默认为30天*/
    override var freeCacheTime: Int = 60 * 60 * 24 * 30
    override var isFileCache: Boolean = false
    override var cacheMode: com.network.library.cache.CacheMode = com.network.library.cache.CacheMode.NO_CACHE


    override fun intercept(chain: Interceptor.Chain): Response {
        return when (cacheMode) {
            com.network.library.cache.CacheMode.IF_CACHE_EXPIRED_REQUEST -> {
                if (NetworkUtil.instance.isInternetConnecting(context!!)) {
                    //有网时的网络缓存处理
                    networkCache(chain)
                } else {
                    //无网时的网络缓存处理
                    freeCache(chain)
                }
            }
            com.network.library.cache.CacheMode.NO_CACHE -> {
                chain.proceed(chain.request())
            }
        }
    }

    private fun networkCache(chain: Interceptor.Chain): Response {

        return fileCache(chain, true)

    }

    private fun freeCache(chain: Interceptor.Chain): Response {
        return fileCache(chain, false)
    }

    /**
     * 文件缓存
     * [chain]Interceptor.Chain
     * [isNetworkConnect]网络是否连接
     */
    private fun fileCache(chain: Interceptor.Chain, isNetworkConnect: Boolean): Response {
        Log.e("---->", "使用文件缓存")
        return chain.proceed(
            //request对象
            chain.request().newBuilder()
                //配置cache-control，有网和无网时的缓存
                .cacheControl(
                    CacheControl.Builder()
                        //指定缓存数据有效时间，没有超出maxStale，不管怎么样都是返回缓存数据，超过maxStale，发起请求，请求失败返回失败
                        .maxStale(
                            if (isNetworkConnect) networkCacheTime else freeCacheTime,
                            TimeUnit.SECONDS
                        )
                        //没有超出maxAge,不管怎么样都是返回缓存数据，超过了maxAge,发起新的请求获取数据更新，请求失败返回缓存数据。
                        //.maxAge(if (isNetworkConnect) networkCacheTime else freeCacheTime, TimeUnit.SECONDS)
                        .build()
                )
                .build()
        ).newBuilder().build()
    }


}