package com.beetech.hsba.ui.home

import android.os.Handler
import android.os.Looper
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.beetech.hsba.R
import com.beetech.hsba.base.BaseFragment
import com.beetech.hsba.base.adapter.adapterHome.HomeAdvertisementAdapter
import com.beetech.hsba.base.adapter.adapterHome.ViewPegerAdapter
import com.beetech.hsba.entity.home.Advertisement
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.home_fragment.*

//@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var pegerAdapter: ViewPegerAdapter
    private lateinit var handle: Handler
    private lateinit var mHomeAdvertisementAdapter: HomeAdvertisementAdapter
    private val list = mutableListOf<Advertisement>()

    override fun backFromAddFragment() {
    }

    override val layoutId: Int
        get() = R.layout.home_fragment

    override fun initView() {
        handle = Handler(Looper.myLooper()!!)
        setUpTabLayout()
        setUpViewClickTab()
    }

    override fun initData() {
        addDataImage()
    }

    override fun initListener() {
        handleViewPegerAdver()
        setUpActivityViewPeger()
    }

    private fun setUpActivityViewPeger() {
        vp_advertisenment.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handle.removeCallbacks(runnable)
                handle.postDelayed(runnable, 5000)
            }
        })
    }

    private fun handleViewPegerAdver() {
        mHomeAdvertisementAdapter = HomeAdvertisementAdapter(list)
        vp_advertisenment.adapter = mHomeAdvertisementAdapter
//        ci_adversements_home.setViewPager(vp_advertisenment)
        worm_dots_indicator.attachTo(vp_advertisenment)
        vp_advertisenment.clipChildren = false
        vp_advertisenment.clipToPadding = false
        vp_advertisenment.offscreenPageLimit = 3
        vp_advertisenment.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        vp_advertisenment.orientation =  ViewPager2.ORIENTATION_HORIZONTAL
        val transformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(20))
        }
        vp_advertisenment.setPageTransformer(transformer)
    }

    override fun backPressed(): Boolean {
        return true
    }
    private fun addDataImage() {
        list.add(Advertisement(R.drawable.bia))
        list.add(Advertisement(R.drawable.chaiceness))
        list.add(Advertisement(R.drawable.fami))
        list.add(Advertisement(R.drawable.gordon))
        list.add(Advertisement(R.drawable.nhan_tan))
    }

    private val runnable = Runnable{
        vp_advertisenment.currentItem = vp_advertisenment.currentItem +1
    }

    private fun setUpTabLayout() {
        pegerAdapter = ViewPegerAdapter(fragmentManager = childFragmentManager, lifecycle)
        vp_home_fragment.adapter = pegerAdapter
        TabLayoutMediator(tl_home, vp_home_fragment){tab, position->
            when(position){
                0-> {
                    tab.text = getString(R.string.lable_specialist)
                }
                1-> tab.text = getString(R.string.lable_service)
            }
        }.attach()
    }

    private fun setUpViewClickTab() {
        tl_home.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tl_home.background = null
                tl_home.setSelectedTabIndicator(null)
                if(tab?.position == 1){
                    setUpViewTab(R.drawable.shape_select_item_1_tablayout, R.drawable.shape_bg_unselect_item_1_tablayout)
                }else{
                    setUpViewTab(R.drawable.shape_select_item_0_tablayout, R.drawable.shape_bg_unselect_item_0_tablayout)
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun setUpViewTab(select: Int, unselect: Int) {
        tl_home.background = ResourcesCompat.getDrawable(requireContext().resources, unselect, null)
        tl_home.setSelectedTabIndicator(ResourcesCompat.getDrawable(requireContext().resources, select, null))

    }

    override fun onPause() {
        handle.removeCallbacks(runnable)
        super.onPause()
    }
}