package com.dakai.android.network.upload

/**
 * 上传进度回调接口
 * @author      ： CuiYancey <cuiyuancheng0322@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-10-23 11:12
 */
interface UploadProgressListener {
    /**
     * 上传进度
     * @param currentBytesCount
     * @param totalBytesCount
     */
    fun onProgress(currentBytesCount: Long, totalBytesCount: Long)
}