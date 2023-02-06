package com.beetech.hsba.ui.homeScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.beetech.hsba.R
import com.beetech.hsba.adapter.adapterHomeScreen.ViewPegerBottomNavigation
import com.beetech.hsba.base.BaseFragment
import com.beetech.hsba.ui.tab_common.TabCommonFragment
import kotlinx.android.synthetic.main.home_screen_fragment.*

class HomeScreenFragment : BaseFragment() {
    private lateinit var mPegerAdapter: ViewPegerBottomNavigation<Fragment>
    private val mFragments = arrayListOf<Fragment>(getFragment(TAB_HOME),
        getFragment(TAB_PATIENT),
        getFragment(TAB_TEST),
        getFragment(TAB_BRIEF),
        getFragment(TAB_ACCOUNT))

    private fun getFragment(index: Int) = TabCommonFragment().apply {
        arguments = Bundle().apply {
            putInt(KEY_TAB_FRAGMENT, index)
        }
    }

    override fun backFromAddFragment() {

    }

    override val layoutId: Int
        get() = R.layout.home_screen_fragment

    override fun initView() {
        setUpView()
    }

    private fun setUpView() {
        mPegerAdapter =
            ViewPegerBottomNavigation(requireContext(), mFragments, childFragmentManager, lifecycle)
//        mPegerAdapter = ViewPegerBottomNavigation(childFragmentManager, lifecycle)
        vp_with_bottom_navigation.adapter = mPegerAdapter
        vp_with_bottom_navigation.isUserInputEnabled = false
        vp_with_bottom_navigation.offscreenPageLimit = mFragments.size
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
                    setUpFragment(TAB_HOME)
                    true
                }
                R.id.menu_patient -> {
                    setUpFragment(TAB_PATIENT)
                    true
                }
                R.id.menu_test -> {
                    setUpFragment(TAB_TEST)
                    true
                }
                R.id.menu_brief -> {
                    setUpFragment(TAB_BRIEF)
                    true
                }
                R.id.menu_account -> {
                    setUpFragment(TAB_ACCOUNT)
                    true
                }
                else -> false
            }
        }
    }

    private fun setUpFragment(position: Int) {
        vp_with_bottom_navigation.setCurrentItem(position, false)
        (mFragments[vp_with_bottom_navigation.currentItem] as? BaseFragment)?.getVC()
            ?.removeAllFragmentExceptFirst()
    }

    override fun backPressed(): Boolean {
        val currentPage = (mFragments[vp_with_bottom_navigation.currentItem] as BaseFragment)
        val currentSize = currentPage.getVC().getCurrentSize()
        return if (currentSize < 2) {
            if (vp_with_bottom_navigation.currentItem == TAB_HOME) {
                true
            } else {
                backHome()
                false
            }
        } else {
            currentPage.getVC().currentFragment?.backPressed() ?: true
        }
    }

    fun backHome() {
        setUpFragment(TAB_HOME)
        bnv_home.selectedItemId = R.id.menu_home
    }

    companion object {
        const val KEY_TAB_FRAGMENT = "KEY_TAB_FRAGMENT"
        const val TAB_ACCOUNT = 4
        const val TAB_BRIEF = 3
        const val TAB_PATIENT = 1
        const val TAB_TEST = 2
        const val TAB_HOME = 0
    }
}