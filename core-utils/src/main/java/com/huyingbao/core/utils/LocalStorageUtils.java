package com.huyingbao.core.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * 本地配出存贮类,保存 int,boolean,String,Object,List
 * <p>
 * Created by liujunfeng on 2019/1/1.
 */
@Singleton
public class LocalStorageUtils {
    private static final String SETTING_NAME = "Setting";
    private SharedPreferences sSharedPreferences;

    @Inject
    LocalStorageUtils(Application application) {
        sSharedPreferences = application.getSharedPreferences(SETTING_NAME, Context.MODE_PRIVATE);
    }

    public void setValue(String key, boolean value) {
        sSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public void setValue(String key, int value) {
        sSharedPreferences.edit().putInt(key, value).apply();
    }

    public void setValue(String key,float value){
        sSharedPreferences.edit().putFloat(key,value).apply();
    }

    public void setValue(String key, long value) {
        sSharedPreferences.edit().putLong(key, value).apply();
    }

    public void setValue(String key, String value) {
        sSharedPreferences.edit().putString(key, value).apply();
    }

    public boolean getValue(String key, boolean defaultValue) {
        return sSharedPreferences.getBoolean(key, defaultValue);
    }

    public int getValue(String key, int defaultValue) {
        return sSharedPreferences.getInt(key, defaultValue);
    }

    public float getVale(String key,float defaultValue){
        return sSharedPreferences.getFloat(key,defaultValue);
    }

    public long getValue(String key, long defaultValue) {
        return sSharedPreferences.getLong(key, defaultValue);
    }

    public String getValue(String key, String defaultValue) {
        return sSharedPreferences.getString(key, defaultValue);
    }


    public <T> void setObject(String key, T t) {
        sSharedPreferences.edit().putString(key, JSON.toJSONString(t)).apply();
    }

    public <T> T getObject(String key, Class<T> cls) {
        String value = sSharedPreferences.getString(key, null);
        try {
            return JSON.parseObject(value, cls);
        } catch (JSONException e) {
            return null;
        }
    }

    public <T> void setList(String key, List<T> list) {
        String value;
        try {
            value = JSON.toJSONString(list);
        } catch (JSONException e) {
            value = null;
        }
        sSharedPreferences.edit().putString(key, value).apply();
    }

    public <T> List<T> getList(String key, Class<T> cls) {
        String value = sSharedPreferences.getString(key, null);
        try {
            return JSON.parseArray(value, cls);
        } catch (JSONException e) {
            return null;
        }
    }

    public void removeKey(String key) {
        sSharedPreferences.edit().remove(key).apply();
    }

    public void clear() {
        sSharedPreferences.edit().clear().apply();
    }
}
