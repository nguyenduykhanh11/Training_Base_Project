package com.beetech.hsba.ui.Splash

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.activityViewModels
import com.beetech.hsba.R
import com.beetech.hsba.base.BaseFragment
import com.beetech.hsba.ui.homeScreen.HomeScreenFragment
import com.beetech.hsba.ui.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment() {
    private val viewModel: SplashViewModel by activityViewModels()
    override fun backFromAddFragment() {

    }
    override val layoutId: Int
        get() = R.layout.splash_fragment

    override fun backPressed(): Boolean {
        return false
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {
        getDataSharedPref()
    }
    private fun getDataSharedPref() {
        val data = viewModel.getTokenLogin()
        if (data.isNullOrEmpty()){
            SetUpMoveToNextScreen(false)
        }else{
            Log.d("this", "$data")
            SetUpMoveToNextScreen(true)
        }

    }

    private fun SetUpMoveToNextScreen(status : Boolean) {
        Looper.getMainLooper()?.let {
            Handler(it).postDelayed({
                if (status){
                    getVC().replaceFragment(HomeScreenFragment::class.java)
                }else{
                    getVC().replaceFragment(LoginFragment::class.java)
                }
            },2000)
        }
    }


}
