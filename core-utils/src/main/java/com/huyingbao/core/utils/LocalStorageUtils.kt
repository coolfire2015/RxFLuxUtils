package com.huyingbao.core.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONException

import javax.inject.Inject
import javax.inject.Singleton

/**
 * 本地配出存贮类,保存 int,boolean,String,Object,List
 *
 *
 * Created by liujunfeng on 2019/1/1.
 */
@Singleton
class LocalStorageUtils @Inject constructor(application: Application) {
    private val sSharedPreferences: SharedPreferences

    init {
        sSharedPreferences = application.getSharedPreferences(SETTING_NAME, Context.MODE_PRIVATE)
    }

    fun setValue(key: String, value: Boolean) {
        sSharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun setValue(key: String, value: Int) {
        sSharedPreferences.edit().putInt(key, value).apply()
    }

    fun setValue(key: String, value: Float) {
        sSharedPreferences.edit().putFloat(key, value).apply()
    }

    fun setValue(key: String, value: Long) {
        sSharedPreferences.edit().putLong(key, value).apply()
    }

    fun setValue(key: String, value: String) {
        sSharedPreferences.edit().putString(key, value).apply()
    }

    fun getValue(key: String, defaultValue: Boolean): Boolean {
        return sSharedPreferences.getBoolean(key, defaultValue)
    }

    fun getValue(key: String, defaultValue: Int): Int {
        return sSharedPreferences.getInt(key, defaultValue)
    }

    fun getVale(key: String, defaultValue: Float): Float {
        return sSharedPreferences.getFloat(key, defaultValue)
    }

    fun getValue(key: String, defaultValue: Long): Long {
        return sSharedPreferences.getLong(key, defaultValue)
    }

    fun getValue(key: String, defaultValue: String): String? {
        return sSharedPreferences.getString(key, defaultValue)
    }


    fun <T> setObject(key: String, t: T) {
        sSharedPreferences.edit().putString(key, JSON.toJSONString(t)).apply()
    }

    fun <T> getObject(key: String, cls: Class<T>): T? {
        val value = sSharedPreferences.getString(key, null)
        try {
            return JSON.parseObject(value, cls)
        } catch (e: JSONException) {
            return null
        }

    }

    fun <T> setList(key: String, list: List<T>) {
        var value: String?
        try {
            value = JSON.toJSONString(list)
        } catch (e: JSONException) {
            value = null
        }

        sSharedPreferences.edit().putString(key, value).apply()
    }

    fun <T> getList(key: String, cls: Class<T>): List<T>? {
        val value = sSharedPreferences.getString(key, null)
        try {
            return JSON.parseArray(value, cls)
        } catch (e: JSONException) {
            return null
        }

    }

    fun removeKey(key: String) {
        sSharedPreferences.edit().remove(key).apply()
    }

    fun clear() {
        sSharedPreferences.edit().clear().apply()
    }

    companion object {
        private val SETTING_NAME = "Setting"
    }
}
