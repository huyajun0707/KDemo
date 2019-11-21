package com.github.netlibrary.utils


import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager


/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-11-20 20:41
 * @depiction   ： 网络工具
 */
class NetworkUtil {


    /**
     * 是否连接网络
     *
     * @return
     */
    @SuppressLint("MissingPermission")
    fun isInternetConnecting(context: Context): Boolean {
        val mgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val infos = mgr.allNetworkInfo
        if (infos != null) {
            for (i in infos.indices) {
                if (infos[i].isConnected) {
                    return true
                }
            }
        }
        return false

    }


    companion object {
        val instance: NetworkUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkUtil()
        }


    }
}
