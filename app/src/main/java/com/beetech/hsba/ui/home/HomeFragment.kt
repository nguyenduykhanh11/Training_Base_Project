package com.beetech.hsba.ui.home

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.beetech.hsba.R
import com.beetech.hsba.adapter.adapterHome.HomeAdvertisementAdapter
import com.beetech.hsba.adapter.adapterHome.HomeSpeciOrServiAdapter
import com.beetech.hsba.base.BaseFragment
import com.beetech.hsba.base.entity.BaseError
import com.beetech.hsba.entity.home.Advertisement
import com.beetech.hsba.entity.home.SpecialtysOrService
import com.beetech.hsba.extension.gone
import com.beetech.hsba.extension.visible
import kotlinx.android.synthetic.main.home_fragment.*
//@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var mAdapter: HomeSpeciOrServiAdapter
    private lateinit var handle: Handler
    private lateinit var mHomeAdvertisementAdapter: HomeAdvertisementAdapter
    private val list = mutableListOf<Advertisement>()

    override fun backFromAddFragment() {
    }

    override val layoutId: Int
        get() = R.layout.home_fragment

    override fun initView() {
        handle = Handler(Looper.myLooper()!!)
        setUpViewRcv()
        setUpViewPegerAdver()
    }

    override fun initData() {
        addDataImage()
        getDataSpecialty()
    }

    override fun initListener() {
        setUpActivityViewPeger()
        setEventSpeciOrService()
    }

    private fun setUpActivityViewPeger() {
        vp_advertisenment.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
//                handle.removeCallbacks(runnable)
//                handle.postDelayed(runnable, 5000)
            }
        })
    }
    private fun setUpViewRcv() {
        mAdapter = HomeSpeciOrServiAdapter()
        rcv_specialty_or_service.run {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }
    }

    private fun setEventSpeciOrService() {
        cl_specialty.setOnClickListener{
            getDataSpecialty()
        }
        cl_service.setOnClickListener {
            viewModel.apply {
                getDataService()
                dataService.observe(viewLifecycleOwner) {
                    handleListResponse(it)
                }
            }
            imv_specialty.visible()
            imv_service.gone()
            rcv_specialty_or_service.setBackgroundResource(R.drawable.shape_boder_radius_tab2_viewpeger)
        }
    }

    private fun getDataSpecialty() {
        viewModel.apply {
            getDataSpecialtys()
            dataSpecialty.observe(viewLifecycleOwner) {
                handleListResponse(it)
            }
        }
        imv_specialty.gone()
        imv_service.visible()
        rcv_specialty_or_service.setBackgroundResource(R.drawable.shape_boder_radius_tab1_viewpeger)
    }

    private fun setUpViewPegerAdver() {
        mHomeAdvertisementAdapter = HomeAdvertisementAdapter(list)
        vp_advertisenment.adapter = mHomeAdvertisementAdapter
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

    override fun onPause() {
//        handle.removeCallbacks(runnable)
        super.onPause()
    }

    override fun <U> getListResponse(data: List<U>?) {
        setUpDataRecyclerView(data)
        super.getListResponse(data)
    }

    override fun handleNetworkError(throwable: Throwable?, isShowDialog: Boolean) {
        Toast.makeText(context, throwable?.message, Toast.LENGTH_SHORT).show()
        super.handleNetworkError(throwable, isShowDialog)
    }

    override fun handleValidateError(throwable: BaseError?) {
        Toast.makeText(context, throwable?.message, Toast.LENGTH_SHORT).show()
        super.handleValidateError(throwable)
    }

    private fun <U> setUpDataRecyclerView(data: List<U>?) {
        mAdapter.setData(data as List<SpecialtysOrService>)
    }
}