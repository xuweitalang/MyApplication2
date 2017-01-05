package com.leador.a360safe.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xuwei on 2016/12/25.
 */

public class ToastUtil {
    public static void showToast(Context context,String msg) {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
