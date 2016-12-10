package com.leador.picassodemo;

import android.app.Application;

import com.baidu.apistore.sdk.ApiStoreSDK;

/**
 * Created by xuwei on 2016/11/22.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApiStoreSDK.init(this,"3ee10d5a2f76866ab1dac41d4236656d");
    }
}
