package com.beetech.hsba.base.adapter.adapterHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.beetech.hsba.extension.KEY_BUNDLE
import com.beetech.hsba.ui.specialist.SpecialtyOrServiceFragment


class ViewPegerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        val fragment = SpecialtyOrServiceFragment()
        fragment.arguments = Bundle().apply {
            putInt(KEY_BUNDLE, position)
        }
        return fragment
//        return when (position) {
//            0 -> {
//                SpecialtyOrServiceFragment()
//            }
//            1 -> {
//                SpecialtyOrServiceFragment()
//            }
//            else -> Fragment()
//        }
    }
}