package com.beetech.hsba

import android.os.Build
import android.view.View
import android.view.WindowManager
import com.beetech.hsba.base.BaseActivity
import com.beetech.hsba.ui.SplashFragment
import com.beetech.hsba.ui.home.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {


    override val layoutResId: Int
        get() = R.layout.activity_main
    override val layoutId: Int
        get() = R.id.container

    override fun initListener() {
    }

    override fun initView() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        getViewController().addFragment(SplashFragment::class.java)
    }

    override fun initData() {
    }
}
