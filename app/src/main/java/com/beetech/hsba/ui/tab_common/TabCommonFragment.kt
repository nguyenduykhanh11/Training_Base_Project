package com.beetech.hsba.ui.tab_common

import android.os.Bundle
import android.util.Log
import android.view.View
import com.beetech.hsba.R
import com.beetech.hsba.base.BaseViewStubFragment
import com.beetech.hsba.base.ViewController
import com.beetech.hsba.ui.account.AccountFragment
import com.beetech.hsba.ui.brief.BriefFragment
import com.beetech.hsba.ui.home.HomeFragment
import com.beetech.hsba.ui.homeScreen.HomeScreenFragment.Companion.KEY_TAB_FRAGMENT
import com.beetech.hsba.ui.homeScreen.HomeScreenFragment.Companion.TAB_ACCOUNT
import com.beetech.hsba.ui.homeScreen.HomeScreenFragment.Companion.TAB_BRIEF
import com.beetech.hsba.ui.homeScreen.HomeScreenFragment.Companion.TAB_HOME
import com.beetech.hsba.ui.homeScreen.HomeScreenFragment.Companion.TAB_PATIENT
import com.beetech.hsba.ui.homeScreen.HomeScreenFragment.Companion.TAB_TEST
import com.beetech.hsba.ui.patient.PatientFragment
import com.beetech.hsba.ui.test.TestFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tab_common.*

@AndroidEntryPoint
class TabCommonFragment : BaseViewStubFragment() {

    override fun backFromAddFragment() {

    }

    override fun backPressed(): Boolean {
        return false
    }

    override fun onCreateViewAfterViewStubInflated(
        inflatedView: View,
        savedInstanceState: Bundle?,
    ) {
        setVC(ViewController(container_tab.id, childFragmentManager))
        arguments?.let {
            if (it.containsKey(KEY_TAB_FRAGMENT)) {
                when (it.getInt(KEY_TAB_FRAGMENT)) {
                    TAB_HOME -> {
                        getVC().addFragment(HomeFragment::class.java)
                    }
                    TAB_PATIENT -> {
                        getVC().addFragment(PatientFragment::class.java)
                    }
                    TAB_TEST -> {
                        getVC().addFragment(TestFragment::class.java)
                    }
                    TAB_BRIEF -> {
                        getVC().addFragment(BriefFragment::class.java)
                    }
                    TAB_ACCOUNT -> {
                        getVC().addFragment(AccountFragment::class.java)
                    }
                }
            }
        }
    }

    override fun getViewStubLayoutResource() = R.layout.fragment_tab_common
}