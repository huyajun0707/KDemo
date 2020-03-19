package com.network.library.cache

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-10-31 14:16
 * @depiction   ： 缓存模式枚举
 */
enum class CacheMode {
    /**不使用缓存*/
    NO_CACHE,
    /**如果缓存过期了（有网默认30s，无网默认30天），那么就请求网络，header使用maxStale设置，请求失败显示失败不使用缓存*/
    IF_CACHE_EXPIRED_REQUEST
}