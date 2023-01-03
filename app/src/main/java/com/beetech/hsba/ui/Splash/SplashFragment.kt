package com.beetech.hsba.ui.Splash

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.viewModels
import com.beetech.hsba.R
import com.beetech.hsba.base.BaseFragment
import com.beetech.hsba.entity.Login.Data
import com.beetech.hsba.extension.*
import com.beetech.hsba.ui.home.HomeFragment
import com.beetech.hsba.ui.login.LoginFragment
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.splash_fragment.*

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
        getDataSharedPref()
    }
    private fun getDataSharedPref() {
        context?.getSharedPreferences(SharePref.MyPref.CategotySharePref, Context.MODE_PRIVATE).let {
            val data = Gson().fromJson(it?.getString(SharePref.KeyPref.CategotySharePref) ,Data::class.java)
            if (data == null){
                SetUpMoveToNextScreen(false)
            }else{
                Log.d("this", "$data")
                SetUpMoveToNextScreen(true)
            }
        }
    }

    private fun SetUpMoveToNextScreen(status : Boolean) {
        Looper.getMainLooper()?.let {
            Handler(it).postDelayed({
                if (status){
                    getVC().replaceFragment(HomeFragment::class.java)
                    Snackbar.make(splash_container, Category.Logged.CategoryError, Snackbar.LENGTH_SHORT)
                        .show()
                }else{
                    getVC().replaceFragment(LoginFragment::class.java)
                }
            },2000)
        }
    }

    private val viewModel: SplashViewModel by viewModels()

}
