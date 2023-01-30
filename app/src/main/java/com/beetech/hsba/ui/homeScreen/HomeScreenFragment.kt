package com.beetech.hsba.ui.homeScreen

import com.beetech.hsba.R
import com.beetech.hsba.adapter.adapterHomeScreen.ViewPegerBottomNavigation
import com.beetech.hsba.base.BaseFragment
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
                    vp_with_bottom_navigation.setCurrentItem(0, false)
                    true
                }
                R.id.menu_patient -> {
                    vp_with_bottom_navigation.setCurrentItem(1, false)
                    true
                }
                R.id.menu_test -> {
                    vp_with_bottom_navigation.setCurrentItem(2, false)
                    true
                }
                R.id.menu_brief -> {

                    vp_with_bottom_navigation.setCurrentItem(3, false)
                    true
                }
                R.id.menu_account -> {
                    vp_with_bottom_navigation.setCurrentItem(4, false)
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