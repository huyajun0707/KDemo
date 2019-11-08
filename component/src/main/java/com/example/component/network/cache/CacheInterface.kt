package com.dakai.android.network.cache

import java.util.concurrent.TimeUnit

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-10-31 14:15
 * @depiction   ： 网络数据缓存接口
 */
interface CacheInterface {
    /**有网时的缓存过期时间，默认为30s*/
    var networkCacheTime: Int
    /**无网时的缓存过期时间，默认为30天*/
    var freeCacheTime: Int
    var isFileCache: Boolean
    var cacheMode: CacheMode

    fun useFileCache(isFileCache: Boolean) {
        this.isFileCache = isFileCache
    }

    /**
     * 设置有网时的缓存过期时间
     * [secondsCacheTime]缓存时间，秒
     */
    fun cacheTimeWithNetwork(secondsCacheTime: Int) {
        this.networkCacheTime = secondsCacheTime
    }

    /**
     * 设置无网时的缓存过期时间
     * [secondsCacheTime]缓存时间，秒
     */
    fun cacheTimeWithFree(secondsCacheTime: Int) {
        this.freeCacheTime = secondsCacheTime
    }
}