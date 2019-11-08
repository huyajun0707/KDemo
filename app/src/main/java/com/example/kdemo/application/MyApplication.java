package com.example.kdemo.application;

import com.example.baselibrary.base.BaseApplication;
import com.example.component.utils.LogUtil;
import com.example.kdemo.BuildConfig;

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
}
