package com.beetech.hsba.ui.specialist

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.beetech.hsba.R
import com.beetech.hsba.adapter.adapterHome.SpecialtyOrServiceAdapter
import com.beetech.hsba.base.BaseFragment
import com.beetech.hsba.base.entity.BaseError
import com.beetech.hsba.entity.home.SpecialtysOrService
import com.beetech.hsba.extension.KEY_BUNDLE
import kotlinx.android.synthetic.main.fragment_specialist_or_service.*
import kotlinx.android.synthetic.main.fragment_test.*

class SpecialtyOrServiceFragment() : BaseFragment() {
    private val viewModel: SpecialtyViewModel by activityViewModels()
    private lateinit var mAdapter: SpecialtyOrServiceAdapter
    private var position = 0

    override fun backFromAddFragment() {
    }

    override val layoutId: Int
        get() = R.layout.fragment_specialist_or_service

    override fun initView() {
    }


    override fun initData() {
        initGetStatusFormHomeFrag()
        getDataSpecialty()
    }


    override fun initListener() {
    }

    override fun backPressed(): Boolean {
        return false
    }

    private fun initGetStatusFormHomeFrag() {
        if (arguments != null){
            position = arguments?.getInt(KEY_BUNDLE)!!
        }
    }

    override fun <U> getListResponse(data: List<U>?) {
        setUpViewRecyclerView(data)
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

    private fun <U> setUpViewRecyclerView(data: List<U>?) {
        mAdapter = SpecialtyOrServiceAdapter(context!!)
        rv_specialty_or_service.apply {
            setAdapter(mAdapter)
            setListLayoutManager(LinearLayoutManager.VERTICAL)
        }
        if (data?.firstOrNull() is SpecialtysOrService) {
            val result = data as List<SpecialtysOrService>
            mAdapter.addModels(result, false)
        }
        rv_specialty_or_service.setOnRefreshListener {
            rv_specialty_or_service.enableRefresh(false)
        }
    }

    private fun getDataSpecialty() {
        if (position == 0){
            viewModel.apply {
                getDataSpecialtys()
                dataSpecialty.observe(viewLifecycleOwner) {
                    handleListResponse(it)
                }
            }
            setUpView(R.drawable.shape_boder_radius_tab1_viewpeger)
        }else{
            viewModel.apply {
                getDataService()
                dataService.observe(viewLifecycleOwner) {
                    handleListResponse(it)
                }
            }
            setUpView(R.drawable.shape_boder_radius_tab2_viewpeger)
        }
    }

    private fun setUpView(drawable: Int) {
        ll_container.setBackgroundResource(drawable)
    }

}