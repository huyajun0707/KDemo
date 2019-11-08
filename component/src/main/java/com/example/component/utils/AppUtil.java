package com.example.component.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;


import java.util.List;

/**
 * @author ： HuYajun <huyajun0707@gmail.com>
 * @version ： 1.0
 * @date ： 2019-10-28 17:53
 * @depiction ：
 */
public class AppUtil {


    private static AppUtil mAppUtil;

    private AppUtil() {
        // cannot be instantiated
    }

    public static synchronized AppUtil getInstance() {
        if (mAppUtil == null) {
            mAppUtil = new AppUtil();
        }
        return mAppUtil;
    }

    public static void releaseInstance() {
        if (mAppUtil != null) {
            mAppUtil = null;
        }
    }


    private static Application application;

    public  Application getApplication() {
        return application;
    }

    public  void setApplication(Application application) {
        AppUtil.application = application;
    }


}
