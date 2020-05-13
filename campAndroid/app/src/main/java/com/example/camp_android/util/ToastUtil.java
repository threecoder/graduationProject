package com.example.camp_android.util;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.example.camp_android.usermodel.LoginActivity;

public class ToastUtil {

    public static void toastText(String msg, Context context){
        Looper.prepare();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

}
