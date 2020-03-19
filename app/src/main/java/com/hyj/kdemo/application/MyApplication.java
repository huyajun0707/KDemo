package com.hyj.kdemo.application;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.hyj.kdemo.BuildConfig;
import com.network.library.utils.LogUtil;
import com.renmai.baselibrary.base.BaseApplication;

/**
 * @author ： HuYajun <huyajun0707@gmail.com>
 * @version ： 1.0
 * @date ： 2019-10-29 10:46
 * @depiction ：
 */
public class MyApplication extends BaseApplication {
    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.Companion.setDebug(BuildConfig.DEBUG);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
