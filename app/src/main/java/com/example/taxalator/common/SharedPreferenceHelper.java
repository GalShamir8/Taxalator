package com.example.taxalator.common;

import static com.example.taxalator.common.Constants.SHARED_PREFERENCE_FILE_NAME;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferenceHelper {
    protected SharedPreferences sp;

    public SharedPreferenceHelper(Context ctx) {
        sp = ctx.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);;
    }

    public boolean getBooleanKey(String key, boolean defaultValue){
        return sp.getBoolean(key, defaultValue);
    }

    public String getStringKey(String key, String defaultValue){
        return sp.getString(key, defaultValue);
    }

    public int getIntKey(String key, int defaultValue){
        return sp.getInt(key, defaultValue);
    }

    public boolean putsStringValue(String key, String value){
        return sp.edit().putString(key, value).commit();
    }

    public boolean putsBooleanValue(String key, boolean value){
        return sp.edit().putBoolean(key, value).commit();
    }

    public boolean putsIntValue(String key, int value){
        return sp.edit().putInt(key, value).commit();
    }
}