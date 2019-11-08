package com.dakai.android.network

import android.util.Log
import com.dakai.android.network.cache.CacheMode
import com.dakai.android.network.interceptor.CacheInterceptor
import com.dakai.android.network.interceptor.CacheNetworkInterceptor
import com.example.component.BuildConfig
import com.example.component.network.adapter.LiveDataCallAdapterFactory
import com.example.component.network.interceptor.LoggerInterceptor
import com.example.component.utils.AppUtil
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @author      ： CuiYancey <cuiyuancheng0322@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-08-06 23:09
 */
class NetworkEngine(private val iscache: Boolean) {

    var isCache = iscache
    /**应用缓存拦截器，执行真正的网络请求前，查找本地缓存。如果找到了那就直接返回response*/
    private var cacheInterceptor: CacheInterceptor? = null
    /**网络缓存拦截器，如果没有本地缓存，就会执行真正的网络请求，请求成功后，可以对数据进行操作，然后将数据缓存起来*/
    private var cacheNetworkInterceptor: CacheNetworkInterceptor? = null
    /**是否是文件缓存，默认为false，既默认使用数据库缓存*/
    private var isFileCache: Boolean = false
    var isCacheConfigureChanged: Boolean = false

    init {
        cacheInterceptor = CacheInterceptor(CacheMode.IF_CACHE_EXPIRED_REQUEST)
        cacheNetworkInterceptor = CacheNetworkInterceptor(CacheMode.IF_CACHE_EXPIRED_REQUEST)
    }

    /**双重检查锁式单例*/
    companion object {
        /**超时时间-默认为6s*/
        const val timeOut: Long = 30
        /**网络请求日志tag*/
        const val NETWORK_TAG: String = "network"

        val instance: NetworkEngine by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkEngine(false)
        }

        val cacheInstance: NetworkEngine by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkEngine(true)
        }
    }

    /**使用数据库缓存*/
    fun useDbCache(): NetworkEngine {
        if (isFileCache) {
            isFileCache = false
            isCacheConfigureChanged = true
        } else isCacheConfigureChanged = false
        cacheInterceptor!!.useFileCache(isFileCache)
        cacheNetworkInterceptor!!.useFileCache(isFileCache)
        return this
    }

    /**使用文件缓存*/
    fun useFileCache(): NetworkEngine {
        if (!isFileCache) {
            isFileCache = true
            isCacheConfigureChanged = true
        } else isCacheConfigureChanged = false
        cacheInterceptor!!.useFileCache(isFileCache)
        cacheNetworkInterceptor!!.useFileCache(isFileCache)
        return this
    }

    /**设置有网缓存时间[secondsCacheTime]，单位：秒*/
    fun setNetworkCacheTime(secondsCacheTime: Int): NetworkEngine {
        cacheInterceptor?.cacheTimeWithNetwork(secondsCacheTime)
        cacheNetworkInterceptor?.cacheTimeWithNetwork(secondsCacheTime)
        return this
    }

    /**设置无网缓存时间[secondsCacheTime]，单位：秒*/
    fun setFreeCacheTime(secondsCacheTime: Int): NetworkEngine {
        cacheInterceptor?.cacheTimeWithFree(secondsCacheTime)
        cacheNetworkInterceptor?.cacheTimeWithFree(secondsCacheTime)
        return this
    }


    /**获取Retrofit配置信息*/
    fun getRetrofitConfig(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    /**获取Retrofit配置信息*/
    fun getRetrofitLiveDataConfig(): Retrofit.Builder {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
    }

    /**获取有缓存设置的OkHttpClient.Builder配置信息*/
     fun getOkHttpClientCacheConfig(): OkHttpClient.Builder {
        return getOkHttpClientConfig()
            //加入缓存拦截器,注意Network与非Network的区别<a href="https://www.jianshu.com/p/e0dd6791653d"></a>
            .addInterceptor(cacheInterceptor!!)//应用拦截器，会在网络请求connect之前执行，如果在这里命中了缓存，那么后面就不会走了
            .addNetworkInterceptor(cacheNetworkInterceptor!!)//网络拦截器，会在网络请求connect之后执行，在这里得到的response是服务器返回的，可以在这里执行数据缓存操作
            .cache(
                //如果是文件缓存就设置缓存文件path，否则设置为null。如果不设置为null的话，OkHttp就会把网络数据写入文件
                if (isFileCache) {
                    Log.e("cui_", "设置缓存路径")
                    Cache(
                        File(AppUtil.getInstance().application.cacheDir, "networkCache"),
                        //设置缓存文件最大为10M，快要满的时候DiskLruCache会删除不常用的缓存
                        10 * 1024 * 1024
                    )
                } else {
                    Log.e("cui_", "不设置文件缓存")
                    null
                }
            )
    }

    /**获取无缓存的OkHttpClient.Builder配置信息*/
     fun getOkHttpClientConfig(): OkHttpClient.Builder {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .readTimeout(timeOut, TimeUnit.SECONDS)//读取超时
            .connectTimeout(timeOut, TimeUnit.SECONDS)//链接超时
            .writeTimeout(timeOut, TimeUnit.SECONDS)//写超时
//            .retryOnConnectionFailure(true)

        if (BuildConfig.DEBUG) {
            //日志打印
            builder.addInterceptor(LoggerInterceptor(NETWORK_TAG, true))
        }
        return builder
    }

    /**获取baseUrl*/
     fun getBaseUrl(): String {
        return "http://ryh-app-test.renmaitech.com/"
    }
}