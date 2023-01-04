package com.beetech.hsba.ui.specialist

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.beetech.hsba.R
import com.beetech.hsba.base.BaseFragment
import com.beetech.hsba.base.adapter.adapterHome.SpecialtyAdapter
import com.beetech.hsba.base.entity.BaseError
import com.beetech.hsba.entity.home.Specialtys
import kotlinx.android.synthetic.main.fragment_specialist.*

class SpecialistFragment : BaseFragment() {
    private val viewModel: SpecialtyViewModel by activityViewModels()
    private lateinit var mAdapter: SpecialtyAdapter
    override fun backFromAddFragment() {
    }

    override val layoutId: Int
        get() = R.layout.fragment_specialist

    override fun initView() {
        createViewModel()
    }


    override fun initData() {
        getDataSpecialty()
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
        mAdapter = SpecialtyAdapter(data as List<Specialtys>)
        rv_specialty.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = mAdapter
        }
    }

    private fun createViewModel() {
        viewModel.getDataSpecialtys()
    }

    private fun getDataSpecialty() {
        viewModel.data.observe(viewLifecycleOwner) {
            handleListResponse(it)
        }
    }

}