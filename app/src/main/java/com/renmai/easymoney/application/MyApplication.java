package com.renmai.easymoney.application;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.github.netlibrary.utils.LogUtil;
import com.renmai.baselibrary.base.BaseApplication;
import com.renmai.easymoney.BuildConfig;

/**
 * @author ： HuYajun <huyajun0707@gmail.com>
 * @version ： 1.0
 * @date ： 2019-10-29 10:46
 * @depiction ：
 */
public class MyApplication extends BaseApplication {
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
