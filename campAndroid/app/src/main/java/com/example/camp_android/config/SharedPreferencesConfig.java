package com.example.camp_android.config;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesConfig {

    private static Context mContext ;

    private static SharedPreferences mySharedPreferences;
    private static SharedPreferences.Editor editor;

    /**
     * 初始化SharedPreferences，必须使用该方法
     */
    public static void init(Context context) {
        mContext = context;
        mySharedPreferences = mContext.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        editor = mySharedPreferences.edit();
    }

    /**
     * 抛出异常
     */
    private static void throwInit(){
        if (mContext == null) {
            throw new NullPointerException("在使用该方法前，需要使用init()方法，推荐将init()放入Application中");
        }
    }

    public static String getString(String key){
        throwInit();
        return mySharedPreferences.getString(key, null);
    }

    public static void putString(String key, String value){
        throwInit();
        editor.putString(key, value);
        editor.apply();
    }

}
