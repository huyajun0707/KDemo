package com.renmai.component.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.renmai.component.network.BaseResponse

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-10-31 19:38
 * @depiction   ：
 */

class TransformationsUtil {
    companion object {
        fun <T> formatData(baseResponse: LiveData<BaseResponse<T>>): LiveData<T>? {
            return Transformations.map(baseResponse) {
                it.data
            }
        }


    }
}