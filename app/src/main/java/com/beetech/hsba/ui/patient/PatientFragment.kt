package com.beetech.hsba.ui.patient

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.beetech.hsba.R
import com.beetech.hsba.adapter.adapterScheduleHealthCheck.ScheduleHealthCheckAdapter
import com.beetech.hsba.base.BaseFragment
import com.beetech.hsba.base.adapter.EndlessLoadingRecyclerViewAdapter
import com.beetech.hsba.base.entity.BaseError
import com.beetech.hsba.entity.patient.ScheduleHealthCheck
import com.beetech.hsba.extension.gone
import com.beetech.hsba.extension.visible
import kotlinx.android.synthetic.main.fragment_patient.*
import kotlinx.android.synthetic.main.fragment_test.my_custom_view
import kotlinx.android.synthetic.main.my_custom_view.*

class PatientFragment : BaseFragment(){
    private val viewModel: PatientViewModel by activityViewModels()
    private lateinit var mAdapter: ScheduleHealthCheckAdapter
    override fun backFromAddFragment() {

    }

    override val layoutId: Int
        get() = R.layout.fragment_patient

    override fun initView() {
        setUpView()
        setUpRecyclerView()
        getDataPage1()
    }

    override fun initData() {
        getDataViewModel()
    }


    override fun initListener() {
        setUpEventRefresh()
        setUpEventLoadMore()
    }

    override fun backPressed(): Boolean {
        return false
    }

    private fun setUpView() {
        my_custom_view.setTitle(getString(R.string.lable_list_patient))
        imb_add.visible()
    }

    override fun getListResponse(data: List<*>?, isRefresh: Boolean, canLoadmore: Boolean) {
        super.getListResponse(data, isRefresh, canLoadmore)
        delayResult()
        mAdapter.enableLoadingMore(canLoadmore)
        if (data?.firstOrNull() is ScheduleHealthCheck) {
            val result = data as List<ScheduleHealthCheck>
            if (isRefresh) {
                mAdapter.clear()
                mAdapter.refresh(result)
            } else {
                mAdapter.hideLoadingItem()
                mAdapter.addModels(result, false)
            }
        }
    }

    private fun delayResult() {
        val handle = Handler(Looper.myLooper()!!)
        handle.postDelayed(runnable, 1000)
    }
    private val runnable = Runnable{
        pgb_load_data_patient.gone()
    }

    override fun handleNetworkError(throwable: Throwable?, isShowDialog: Boolean) {
        Toast.makeText(context, throwable?.message, Toast.LENGTH_SHORT).show()
        super.handleNetworkError(throwable, isShowDialog)
    }

    override fun handleValidateError(throwable: BaseError?) {
        Toast.makeText(context, throwable?.message, Toast.LENGTH_SHORT).show()
        super.handleValidateError(throwable)
    }

    private fun setUpRecyclerView() {
        mAdapter = ScheduleHealthCheckAdapter(context!!)
        patient_recycler_view.apply {
            setAdapter(mAdapter)
            setListLayoutManager(LinearLayoutManager.VERTICAL)
        }
    }

    private fun setUpEventRefresh() {
        patient_recycler_view.setOnRefreshListener {
            getDataPage1()
        }
    }

    private fun getDataPage1() {
        patient_recycler_view.enableRefresh(false)
        viewModel.apply {
            getScheduleHealthCheck(true)
        }
    }

    private fun setUpEventLoadMore() {
        patient_recycler_view.setOnLoadingMoreListener(object :
            EndlessLoadingRecyclerViewAdapter.OnLoadingMoreListener {
            override fun onLoadMore() {
                mAdapter.showLoadingItem(false)
                viewModel.apply {
                    getScheduleHealthCheck()
                }
            }
        })
    }

    private fun getDataViewModel() {
        viewModel.apply {
            data.observe(viewLifecycleOwner) {
                handleLoadMoreResponse(it)
            }
        }
    }


}