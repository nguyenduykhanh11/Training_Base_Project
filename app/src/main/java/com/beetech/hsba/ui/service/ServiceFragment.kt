package com.beetech.hsba.ui.service

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.beetech.hsba.R
import com.beetech.hsba.base.BaseFragment
import com.beetech.hsba.base.adapter.adapterHome.ServiceAdapter
import com.beetech.hsba.base.entity.BaseError
import com.beetech.hsba.entity.home.Services
import kotlinx.android.synthetic.main.fragment_service.*

class ServiceFragment : BaseFragment() {
    private val viewModel: ServiceViewModel by activityViewModels()
    private lateinit var mAdapter: ServiceAdapter

    override fun backFromAddFragment() {

    }

    override val layoutId: Int
        get() = R.layout.fragment_service

    override fun initView() {
        createViewModel()
    }

    override fun initData() {
        getDataService()
    }

    override fun initListener() {

    }

    override fun backPressed(): Boolean {
        return false
    }

    override fun <U> getListResponse(data: List<U>?) {
        Log.d("this", data.toString())
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
        mAdapter = ServiceAdapter(data as List<Services>)
        rv_service.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = mAdapter
        }
    }

    private fun createViewModel() {
        viewModel.getDataService()
    }

    private fun getDataService() {
        viewModel.data.observe(viewLifecycleOwner) {
            handleListResponse(it)
        }
    }
}