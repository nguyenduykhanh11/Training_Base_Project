package com.beetech.hsba.ui.Splash

import com.beetech.hsba.base.BaseViewModel
import com.beetech.hsba.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(var repo: Repository) : BaseViewModel() {
    fun getTokenLogin(): String{
        val token = repo.getDataLogin().accessToken
        if (token != null){
            return token
        }
        return ""
    }
}
