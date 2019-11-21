package com.renmai.component.utils

import com.google.common.collect.Lists


object JsonFormatUtil {

    fun formatJson(json: String?): String {
        val fillStringUnit = "\t"
        if (json == null || json.trim { it <= ' ' }.length == 0) {
            return ""
        }
        var fixedLenth = 0
        val tokenList = Lists.newArrayList<String>()
        run {
            var jsonTemp: String = json
            while (jsonTemp.length > 0) {
                var token = getToken(jsonTemp)
                jsonTemp = jsonTemp.substring(token.length)
                token = token.trim { it <= ' ' }
                tokenList.add(token)
            }
        }

        for (i in tokenList.indices) {
            val token = tokenList.get(i)
            val length = token.toByteArray().size
            if (length > fixedLenth && i < tokenList.size - 1 && tokenList.get(i + 1) == ":") {
                fixedLenth = length
            }
        }

        val buf = StringBuilder()
        var count = 0
        var i = 0
        while (tokenList.size > i) {

            val token = tokenList.get(i)

            if (token == ",") {
                buf.append(token)
                doFill(buf, count, fillStringUnit)
                i++
                continue
            }
            if (token == ":") {
                buf.append(" ").append(token).append(" ")
                i++
                continue
            }
            if (token == "{") {
                val nextToken = tokenList.get(i + 1)
                if (nextToken == "}") {
                    i++
                    buf.append("{ }")
                } else {
                    count++
                    buf.append(token)
                    doFill(buf, count, fillStringUnit)
                }
                i++
                continue
            }
            if (token == "}") {
                count--
                doFill(buf, count, fillStringUnit)
                buf.append(token)
                i++
                continue
            }
            if (token == "[") {
                val nextToken = tokenList.get(i + 1)
                if (nextToken == "]") {
                    i++
                    buf.append("[ ]")
                } else {
                    count++
                    buf.append(token)
                    doFill(buf, count, fillStringUnit)
                }
                i++
                continue
            }
            if (token == "]") {
                count--
                doFill(buf, count, fillStringUnit)
                buf.append(token)
                i++
                continue
            }

            buf.append(token)
            if (i < tokenList.size - 1 && tokenList.get(i + 1) == ":") {
                val fillLength = fixedLenth - token.toByteArray().size
                if (fillLength > 0) {
                    for (j in 0 until fillLength) {
                        buf.append(" ")
                    }
                }
            }
            i++
        }
        return buf.toString()
    }

    private fun getToken(json: String): String {
        var json = json
        val buf = StringBuilder()
        var isInYinHao = false
        while (json.length > 0) {
            val token = json.substring(0, 1)
            json = json.substring(1)

            if (!isInYinHao && (token == ":" || token == "{" || token == "}"
                        || token == "[" || token == "]"
                        || token == ",")
            ) {
                if (buf.toString().trim { it <= ' ' }.length == 0) {
                    buf.append(token)
                }

                break
            }

            if (token == "\\") {
                buf.append(token)
                buf.append(json.substring(0, 1))
                json = json.substring(1)
                continue
            }
            if (token == "\"") {
                buf.append(token)
                if (isInYinHao) {
                    break
                } else {
                    isInYinHao = true
                    continue
                }
            }
            buf.append(token)
        }
        return buf.toString()
    }

    private fun doFill(buf: StringBuilder, count: Int, fillStringUnit: String) {
        buf.append("\n")
        for (i in 0 until count) {
            buf.append(fillStringUnit)
        }
    }
}
