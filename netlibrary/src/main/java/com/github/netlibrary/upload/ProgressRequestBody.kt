package com.github.netlibrary.upload

import okhttp3.MediaType
import okhttp3.RequestBody
import okio.Buffer
import okio.BufferedSink
import okio.ForwardingSink
import okio.Okio
import java.io.IOException

/**
 * 上传文件时使用的有进度条的RequestBody
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-10-23 10:47
 */
class ProgressRequestBody : RequestBody {
    /**实际的请求体*/
    private var requestBody: RequestBody? = null
    /**进度回调监听*/
    private var progressListener: UploadProgressListener? = null
    private var bufferedSink: BufferedSink? = null

    constructor(requestBody: RequestBody?, progressListener: UploadProgressListener) {
        this.requestBody = requestBody
        this.progressListener = progressListener
    }

    /**
     * 返回响应体的contentType
     * @return MediaType?
     */
    override fun contentType(): MediaType? {
        return requestBody?.contentType()
    }

    /**
     * 返回文件总的字节大小，如果文件大小获取失败则返回-1
     * @return Long
     */
    override fun contentLength(): Long {
        return try {
            requestBody?.contentLength() ?: -1
        } catch (e: IOException) {
            e.printStackTrace()
            -1
        }
    }

    override fun writeTo(sink: BufferedSink) {
        if (bufferedSink == null) {
            bufferedSink = Okio.buffer(object : ForwardingSink(sink){
                //当前已经写入的字节数
                var currentBytesCount: Long = 0
                val totalBytesCount: Long = contentLength()

                override fun write(source: Buffer, byteCount: Long) {
                    super.write(source, byteCount)
                    //更新当前写入的字节数
                    currentBytesCount += byteCount
                    progressListener?.onProgress(currentBytesCount, totalBytesCount)
                }
            })
        }
        requestBody?.writeTo(bufferedSink!!)
        //必须调用flush，否则最后一部分数据可能不会被写入
        bufferedSink?.flush()
    }
}