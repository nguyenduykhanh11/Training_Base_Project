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
import com.beetech.hsba.entity.home.Advertisement
import com.beetech.hsba.ui.adapter.ViewPegerAdapter
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
    }

    override fun initData() {
        addDataImage()
    }

    override fun initListener() {
        handleViewPegerAdver()
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
            addTransformer(MarginPageTransformer(10))
        }
        vp_advertisenment.setPageTransformer(transformer)
        vp_advertisenment.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handle.removeCallbacks(runnable)
                handle.postDelayed(runnable, 5000)
            }
        })
    }

    override fun backPressed(): Boolean {
        return false
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
                0-> tab.text = "Chuyên khoa"
                1-> tab.text = "Dịch vụ"
            }
        }.attach()


        tl_home.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab?.position == 1){
                    tl_home.setSelectedTabIndicator(context?.getDrawable(R.drawable.shape_select_item_1_tablayout))
                    tl_home.background = ResourcesCompat.getDrawable(requireContext().resources,  R.drawable.shape_bg_unselect_item_1_tablayout, null)
                }else{
                    tl_home.setSelectedTabIndicator(context?.getDrawable(R.drawable.shape_select_item_0_tablayout))
                    tl_home.background = ResourcesCompat.getDrawable(requireContext().resources,  R.drawable.shape_bg_unselect_item_0_tablayout, null)
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    override fun onPause() {
        handle.removeCallbacks(runnable)
        super.onPause()
    }
}