package com.renmai.easymoney.entity

import android.os.Parcelable
import android.text.TextUtils
import com.renmai.component.entity.UserInfo
import kotlinx.android.parcel.Parcelize

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-12-17 15:16
 * @depiction   ：
 */


@Parcelize
data class UserInfo(
    var city: String,
    var cityCode: String,
    var country: String,
    var education: String,
    var email: String,
    var gender: Int,
    var homeAddress: String,
    var idNo: String,
    var jobTitle: String,
    var monthIncome: String,
    var province: String,
    var provinceCode: String,
    var userId: Int,
    var userName: String,
    var bankCard: UserInfo.BankCard?
) : Parcelable {

    /**
     * city : 北京
     * cityCode : 110001
     * country : 中国
     * education : 本科
     * email : email@email.com
     * gender : 1
     * homeAddress : 北京东城银河soho
     * idNo : 11000010303
     * jobTitle : 总经理
     * monthIncome : 1000-5000
     * province : 北京
     * provinceCode : 100000
     * userId : 110000
     * userName : 孙悟空
     */

    val provinceAndCity: String
        get() {
            var str = ""
            if (!TextUtils.isEmpty(province)) {
                str = "$province "
            }
            if (!TextUtils.isEmpty(city)) {
                str += city!!
            }
            return str
        }

}