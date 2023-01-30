package com.beetech.hsba.adapter.adapterHomeScreen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.beetech.hsba.base.BaseFragment
import com.beetech.hsba.ui.account.AccountFragment
import com.beetech.hsba.ui.brief.BriefFragment
import com.beetech.hsba.ui.home.HomeFragment
import com.beetech.hsba.ui.patient.PatientFragment
import com.beetech.hsba.ui.test.TestFragment

class ViewPegerBottomNavigation(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomeFragment()
            }
            1 -> {
                PatientFragment()
            }
            2 -> {
                TestFragment()
            }
            3 -> {
                BriefFragment()
            }
            4 -> {
                AccountFragment()
            }
            else -> Fragment()
        }
    }
}