package com.beetech.hsba.base.adapter.adapterHome

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.beetech.hsba.ui.specialist.SpecialtyOrServiceFragment

class ViewPegerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                SpecialtyOrServiceFragment(true)
            }
            1 -> {
                SpecialtyOrServiceFragment(false)
            }
            else -> Fragment()
        }
    }
}