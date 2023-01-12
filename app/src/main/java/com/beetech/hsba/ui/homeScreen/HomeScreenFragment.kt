package com.beetech.hsba.ui.homeScreen

import android.util.Log
import com.beetech.hsba.R
import com.beetech.hsba.base.BaseFragment
import com.beetech.hsba.adapter.adapterHomeScreen.ViewPegerBottomNavigation
import kotlinx.android.synthetic.main.home_screen_fragment.*

class HomeScreenFragment: BaseFragment() {
    private lateinit var mPegerAdapter: ViewPegerBottomNavigation
    override fun backFromAddFragment() {

    }

    override val layoutId: Int
        get() = R.layout.home_screen_fragment

    override fun initView() {
        setUpView()
    }

    private fun setUpView() {
        vp_with_bottom_navigation.isUserInputEnabled = false
        mPegerAdapter = ViewPegerBottomNavigation(fragmentManager = childFragmentManager, lifecycle)
        vp_with_bottom_navigation.adapter = mPegerAdapter
    }

    override fun initData() {

    }

    override fun initListener() {
        setUpClickItemBottomNavigation()
    }

    private fun setUpClickItemBottomNavigation() {
        bnv_home.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    vp_with_bottom_navigation.currentItem = 0
                    true
                }
                R.id.menu_patient -> {
                    vp_with_bottom_navigation.currentItem = 1
                    true
                }
                R.id.menu_test -> {
                    vp_with_bottom_navigation.currentItem = 2
                    true
                }
                R.id.menu_brief -> {
                    vp_with_bottom_navigation.currentItem = 3
                    true
                }
                R.id.menu_account -> {
                    vp_with_bottom_navigation.currentItem = 4
                    true
                }
                else -> false
            }
        }
    }


    override fun backPressed(): Boolean {
        return true
    }
}