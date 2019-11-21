package com.renmai.component.utils;


import android.os.Parcelable;
import android.text.TextUtils;

import com.renmai.component.entity.UserInfo;
import com.tencent.mmkv.MMKV;

/**
 * @author ： HuYajun <huyajun0707@gmail.com>
 * @version ： 1.0
 * @date ： 2019-11-20 13:58
 * @depiction ：
 */
public class UserUtils {

    private static String TAG = UserUtils.class.getSimpleName();
    private static final String PARAM_TOKEN = TAG + "_param_token";
    private static final String PARAM_USER_INFO = TAG + "_param_user_info";
    private static final String PARAM_DEVICE_ID = TAG + "_param_device_id";
    private static final String PARAM_SERVICE_TEL_PHONE = TAG + "_param_service_tel_phone";
    private static final String PARAM_REAL_NAME_INFORMATION_SHOW = TAG + "_real_name_information_show";

    public static void setToken(String token) {
        MMKV.defaultMMKV().encode(PARAM_TOKEN, token);
    }

    public static String getToken() {
        return MMKV.defaultMMKV().decodeString(PARAM_TOKEN);
    }

    public static boolean isLogin() {
        return !TextUtils.isEmpty(getToken());
    }

    public static void clearToken() {
        MMKV.defaultMMKV().removeValueForKey(PARAM_TOKEN);
    }

    public static void saveUserInfo(Parcelable value) {
        MMKV.defaultMMKV().encode(PARAM_USER_INFO, value);
    }

    public static void saveDeviceId(String deviceId) {
        if (TextUtils.isEmpty(MMKV.defaultMMKV().decodeString(PARAM_DEVICE_ID))) {
            MMKV.defaultMMKV().encode(PARAM_DEVICE_ID, deviceId);
        }
    }

    public static String getDeviceId() {
        return MMKV.defaultMMKV().decodeString(PARAM_DEVICE_ID);
    }

    public static UserInfo getUserInfo() {
        return MMKV.defaultMMKV().decodeParcelable(PARAM_USER_INFO, UserInfo.class);
    }

    public static void clearUserInfo() {
        MMKV.defaultMMKV().removeValueForKey(PARAM_USER_INFO);
    }


    public static void saveServiceTelPhone(String customerPhone) {
        MMKV.defaultMMKV().encode(PARAM_SERVICE_TEL_PHONE, customerPhone);
    }

    public static String getServiceTelPhone() {
        return MMKV.defaultMMKV().decodeString(PARAM_SERVICE_TEL_PHONE);
    }


    public static void saveRealNameInformationStatus(boolean isShow) {
//        UserInfo.DataBean userInfo = getUserInfo();
//        if (userInfo != null)
        MMKV.defaultMMKV().putBoolean(PARAM_REAL_NAME_INFORMATION_SHOW, isShow);
    }

    public static boolean getRealNameInformationStatus() {
//        UserInfo.DataBean userInfo = getUserInfo();
//        if (userInfo != null)
//            return MMKV.defaultMMKV().getBoolean(PARAM_REAL_NAME_INFORMATION_SHOW, true);
        return MMKV.defaultMMKV().getBoolean(PARAM_REAL_NAME_INFORMATION_SHOW, true);
    }


}
