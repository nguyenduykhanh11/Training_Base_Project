package com.beetech.hsba.di.module

import android.content.Context
import android.util.Log
import com.beetech.hsba.BaseApplication
import com.beetech.hsba.entity.Login.Data
import com.beetech.hsba.extension.CategotySharePref
import com.beetech.hsba.extension.SharePref
import com.beetech.hsba.extension.getString
import com.google.gson.Gson
import okhttp3.internal.cache2.Relay.Companion.edit
import javax.inject.Inject

class SharePrefe @Inject constructor(): SharePreference{
    override fun checkSharePref(): Data {
        BaseApplication.context.getSharedPreferences(SharePref.MyPref.CategotySharePref, Context.MODE_PRIVATE).let {
            return Gson().fromJson(it?.getString(SharePref.KeyPref.CategotySharePref) ,Data::class.java)?: Data()
        }
    }

    override fun clearSharePref(){
        val sharedPref = BaseApplication.context.getSharedPreferences(SharePref.MyPref.CategotySharePref, Context.MODE_PRIVATE)
        sharedPref.edit().clear().apply()
    }
}