package com.beetech.hsba.ui.Splash

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.viewModels
import com.beetech.hsba.R
import com.beetech.hsba.base.BaseFragment
import com.beetech.hsba.ui.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment() {


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
        Looper.getMainLooper()?.let {
            Handler(it).postDelayed({
                getVC().replaceFragment(LoginFragment::class.java)
            },2000)
        }

    }

    private val viewModel: SplashViewModel by viewModels()

}
