package com.leador.picassodemo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by xuwei on 2016/12/3.
 */
public class GsonUtil {
    private static Gson gson;
    /*
    获取Gson对象
     */
    public static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        }
        return gson;
    }
}
