package com.beetech.hsba.ui.Splash

import com.beetech.hsba.base.BaseViewModel
import com.beetech.hsba.di.module.SharePreference
import com.beetech.hsba.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(var repo: Repository, private val sharePreference: SharePreference) : BaseViewModel() {
    fun getTokenLogin(): String{
        return sharePreference.checkSharePref().accessToken?:""
    }
}
