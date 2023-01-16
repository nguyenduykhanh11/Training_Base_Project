package com.beetech.hsba.ui.patient

import android.util.Log
import com.beetech.hsba.R
import com.beetech.hsba.base.BaseFragment

class PatientFragment : BaseFragment(){
    override fun backFromAddFragment() {

    }

    override val layoutId: Int
        get() = R.layout.fragment_patient

    override fun initView() {
//        tv_title.text = "test"
    }

    override fun initData() {

    }

    override fun initListener() {

    }

    override fun backPressed(): Boolean {
        return false
    }

    override fun onResume(){
        Log.d("test", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("test", "onPause")
        super.onPause()
    }


}