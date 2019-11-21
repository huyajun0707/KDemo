package com.renmai.component.utils

import android.content.res.Resources

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-11-13 15:37
 * @depiction   ：
 */
class DensityUtil {
    companion object {
        val instance: DensityUtil by lazy { DensityUtil() }
    }

    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    fun getDensity(): Float {
        return Resources.getSystem().displayMetrics.density
    }

    fun getDensityDpi(): Int {
        return Resources.getSystem().displayMetrics.densityDpi
    }

    fun dp2px(dpValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun px2dp(pxValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    fun dp2px(resources: Resources, dp: Float): Float {
        val scale = resources.displayMetrics.density
        return dp * scale + 0.5f
    }

    fun sp2px(sp: Float): Float {
        val scale = Resources.getSystem().displayMetrics.scaledDensity
        return sp * scale
    }
}