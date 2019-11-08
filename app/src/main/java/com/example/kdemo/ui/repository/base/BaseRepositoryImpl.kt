package com.example.kdemo.ui.repository.base

import com.dakai.android.network.ApiService
import com.example.baselibrary.repository.BaseRepository

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-11-06 17:06
 * @depiction   ：
 */

open class BaseRepositoryImpl : BaseRepository(){

    protected var apiService: ApiService = ApiService.getApi()


}