package com.renmai.baselibrary.base.viewmodel

import androidx.lifecycle.ViewModel
import com.renmai.component.utils.ReflectionUtils

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2020-01-10 10:10
 * @depiction   ：
 */
open class BaseViewModel<T:BaseRepository> : ViewModel() {
    val repository = ReflectionUtils.getNewInstance(this,0) as T

}