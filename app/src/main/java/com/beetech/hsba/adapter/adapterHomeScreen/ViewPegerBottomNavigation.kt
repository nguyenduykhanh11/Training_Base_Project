package com.beetech.hsba.adapter.adapterHomeScreen

import android.content.Context
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

class ViewPegerBottomNavigation<T>: FragmentStateAdapter{
//    override fun getItemCount(): Int = 5
//
//    override fun createFragment(position: Int): Fragment {
//        return when (position) {
//            0 -> {
//                HomeFragment()
//            }
//            1 -> {
//                PatientFragment()
//            }
//            2 -> {
//                TestFragment()
//            }
//            3 -> {
//                BriefFragment()
//            }
//            4 -> {
//                AccountFragment()
//            }
//            else -> Fragment()
//        }
//    }

    private var context: Context
    private var itemsSource: List<T>

    constructor(
        context: Context,
        itemsSource: MutableList<T>,
        fragmentManager: FragmentManager?,
        lifecycle: Lifecycle?
    ) : super(
        fragmentManager!!, lifecycle!!
    ) {
        this.context = context
        this.itemsSource = itemsSource
    }

    constructor(
        context: Context,
        itemsSource: List<T>,
        fragment: Fragment?,
    ) : super(
        fragment!!
    ) {
        this.context = context
        this.itemsSource = itemsSource
    }

    override fun createFragment(position: Int): Fragment {
        return itemsSource[position] as Fragment
    }

    override fun getItemCount(): Int {
        return itemsSource.size
    }

    override fun getItemId(position: Int): Long {
        return itemsSource[position].hashCode().toLong()
    }

    override fun containsItem(itemId: Long): Boolean {
        return !itemsSource.none { it.hashCode().toLong() == itemId }
    }
}