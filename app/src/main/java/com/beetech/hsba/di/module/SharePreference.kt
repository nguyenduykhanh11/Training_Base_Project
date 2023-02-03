package com.beetech.hsba.di.module

import com.beetech.hsba.entity.Login.Data

interface SharePreference {
    fun checkSharePref(): Data
    fun clearSharePref()
}