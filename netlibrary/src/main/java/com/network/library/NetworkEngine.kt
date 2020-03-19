package com.network.library

import android.app.Application
import android.content.Context
import android.util.Log
import com.github.netlibrary.cache.adapter.LiveDataCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit



/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-08-06 23:09
 */
class NetworkEngine {

    private constructor()

    /**应用缓存拦截器，执行真正的网络请求前，查找本地缓存。如果找到了那就直接返回response*/
    private var cacheInterceptor: com.network.library.interceptor.CacheInterceptor? = null
    /**网络缓存拦截器，如果没有本地缓存，就会执行真正的网络请求，请求成功后，可以对数据进行操作，然后将数据缓存起来*/
    private var cacheNetworkInterceptor: com.network.library.interceptor.CacheNetworkInterceptor? = null
    /**是否是文件缓存，默认为false，既默认使用数据库缓存*/
    private var isFileCache: Boolean = false
    var isCacheConfigureChanged: Boolean = false

    fun init(context: Context) {
        cacheInterceptor = com.network.library.interceptor.CacheInterceptor(
            com.network.library.cache.CacheMode.IF_CACHE_EXPIRED_REQUEST,
            context
        )
        cacheNetworkInterceptor =
            com.network.library.interceptor.CacheNetworkInterceptor(com.network.library.cache.CacheMode.IF_CACHE_EXPIRED_REQUEST)
    }

    companion object {

        fun builder(): com.network.library.NetworkEngine.Builder {
            return com.network.library.NetworkEngine.Builder()
        }
    }


    /**使用文件缓存*/
    fun useFileCache(): com.network.library.NetworkEngine {
        if (!isFileCache) {
            isFileCache = true
            isCacheConfigureChanged = true
        } else isCacheConfigureChanged = false
        cacheInterceptor!!.useFileCache(isFileCache)
        cacheNetworkInterceptor!!.useFileCache(isFileCache)
        return this
    }

    /**设置有网缓存时间[secondsCacheTime]，单位：秒*/
    fun setNetworkCacheTime(secondsCacheTime: Int): com.network.library.NetworkEngine {
        cacheInterceptor?.cacheTimeWithNetwork(secondsCacheTime)
        cacheNetworkInterceptor?.cacheTimeWithNetwork(secondsCacheTime)
        return this
    }

    /**设置无网缓存时间[secondsCacheTime]，单位：秒*/
    fun setFreeCacheTime(secondsCacheTime: Int): com.network.library.NetworkEngine {
        cacheInterceptor?.cacheTimeWithFree(secondsCacheTime)
        cacheNetworkInterceptor?.cacheTimeWithFree(secondsCacheTime)
        return this
    }


    /**获取Retrofit配置信息*/
    fun getRetrofitConfig(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(com.network.library.interceptor.NullOnEmptyConverterFactory())
            .addConverterFactory(com.network.library.adapter.GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    /**获取协程Retrofit配置信息*/
    fun getRetrofitCoroutineConfig(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(com.network.library.adapter.GsonConverterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
    }

    /**获取Retrofit livedata配置信息*/
    fun getRetrofitLiveDataConfig(): Retrofit.Builder {
        return Retrofit.Builder()
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
    }

    /**获取有缓存设置的OkHttpClient.Builder配置信息*/
    fun getOkHttpClientCacheConfig(
        headerMap: Map<String, () -> String>,
        timeOut: Long,
        tag: String,
        debug: Boolean,
        application: Application
    ): OkHttpClient.Builder {
        return getOkHttpClientConfig(headerMap, timeOut, tag, debug)
            //加入缓存拦截器,注意Network与非Network的区别<a href="https://www.jianshu.com/p/e0dd6791653d"></a>
            .addInterceptor(cacheInterceptor!!)//应用拦截器，会在网络请求connect之前执行，如果在这里命中了缓存，那么后面就不会走了
            .addNetworkInterceptor(cacheNetworkInterceptor!!)//网络拦截器，会在网络请求connect之后执行，在这里得到的response是服务器返回的，可以在这里执行数据缓存操作
            .cache(
                //如果是文件缓存就设置缓存文件path，否则设置为null。如果不设置为null的话，OkHttp就会把网络数据写入文件
                if (isFileCache) {
                    Log.e("---->", "设置缓存路径")
                    Cache(
                        File(application?.cacheDir, "networkCache"),
                        //设置缓存文件最大为10M，快要满的时候DiskLruCache会删除不常用的缓存
                        10 * 1024 * 1024
                    )
                } else {
                    Log.e("---->", "不设置文件缓存")
                    null
                }
            )
    }

    /**获取无缓存的OkHttpClient.Builder配置信息*/
    fun getOkHttpClientConfig(
        headerMap: Map<String, () -> String>,
        timeOut: Long,
        tag: String,
        debug: Boolean
    ): OkHttpClient.Builder {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
//            .readTimeout(timeOut, TimeUnit.SECONDS)//读取超时
            .connectTimeout(timeOut, TimeUnit.SECONDS)//链接超时
            .writeTimeout(timeOut, TimeUnit.SECONDS)//写超时
            .sslSocketFactory(com.network.library.https.HttpsFactory.getSslSocketFactory(null, null))
            .addInterceptor(com.network.library.interceptor.HeaderInterceptor(headerMap))
//            .retryOnConnectionFailure(true)
        if (debug) {
            //日志打印
            builder.addInterceptor(com.network.library.interceptor.LoggerInterceptor(tag, true))
        }
        return builder
    }

    class Builder {
        /**超时时间-默认为6s*/
        private var timeOut: Long = 30
        private var debug: Boolean = false
        /**网络请求日志tag*/
        private var logTag: String = "network"
        private var isCache = false
        private var application: Application? = null
        private var baseUrl: String? = null
        private var retrofit: Retrofit? = null
        private var headerMap: Map<String, () -> String>? = null


        fun setTimeOut(timeOut: Long): com.network.library.NetworkEngine.Builder {
            this.timeOut = timeOut
            return this
        }

        fun setDebug(debug: Boolean): com.network.library.NetworkEngine.Builder {
            this.debug = debug
            return this
        }

        fun setLogTag(tag: String): com.network.library.NetworkEngine.Builder {
            this.logTag = tag
            return this
        }

        fun isCache(cache: Boolean): com.network.library.NetworkEngine.Builder {
            this.isCache = cache
            return this
        }

        fun setHeaderMap(map: Map<String, () -> String>): com.network.library.NetworkEngine.Builder {
            this.headerMap = map
            return this
        }

        fun baseUrl(url: String): com.network.library.NetworkEngine.Builder {
            this.baseUrl = url
            return this
        }


        fun setApplication(application: Application): com.network.library.NetworkEngine.Builder {
            this.application = application
            return this
        }


        fun build(): com.network.library.NetworkEngine.Builder {
            var networkEngine = com.network.library.NetworkEngine()
            if (application == null) {
                throw Exception("网络配置不完整，请检查...")
            }
            networkEngine.init(application!!)
            var builder: OkHttpClient.Builder = if (isCache) {
                networkEngine.getOkHttpClientCacheConfig(
                    headerMap!!,
                    timeOut,
                    logTag,
                    debug,
                    application!!
                )
            } else {
                networkEngine.getOkHttpClientConfig(headerMap!!, timeOut, logTag, debug)
            }

            retrofit = networkEngine.getRetrofitCoroutineConfig().let {
                it.client(builder.build()).baseUrl(baseUrl).build()
            }
            return this
        }


        fun <T> create(clazz: Class<T>): T {
            return retrofit!!.create(clazz)
        }

    }

}

