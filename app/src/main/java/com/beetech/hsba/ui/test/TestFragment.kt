package com.beetech.hsba.ui.test

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.beetech.hsba.R
import com.beetech.hsba.adapter.adapterTest.TestAdapter
import com.beetech.hsba.base.BaseFragment
import com.beetech.hsba.base.adapter.EndlessLoadingRecyclerViewAdapter
import com.beetech.hsba.base.entity.BaseError
import com.beetech.hsba.entity.Test.Test
import com.beetech.hsba.extension.gone
import com.beetech.hsba.extension.visible
import com.beetech.hsba.ui.homeScreen.HomeScreenFragment
import kotlinx.android.synthetic.main.fragment_test.*
import kotlinx.android.synthetic.main.my_custom_view.*

class   TestFragment : BaseFragment() {
    private lateinit var mAdapter: TestAdapter
    private val viewModel: TestViewModel by activityViewModels()
    override fun backFromAddFragment() {

    }

    override val layoutId: Int
        get() = R.layout.fragment_test

    override fun initView() {
        setUpView()
        setUpRecyclerView()
    }

    private fun setUpView() {
        my_custom_view.setTitle(getString(R.string.medical_examination_history))
        imb_add.visible()
    }


    override fun initData() {
        getDataViewModel()
        getDataPage1()
    }

    override fun initListener() {
        setUpEventRefresh()
        setUpEventLoadMore()
        setEventBackFragment()
    }

    override fun onResume() {
        super.onResume()
    }
    private fun setEventBackFragment() {
        my_custom_view.onBackClick = {
            (parentFragment?.parentFragment as HomeScreenFragment).backPressed()
        }
    }

    override fun backPressed(): Boolean {
        return true
    }
    override fun getListResponse(data: List<*>?, isRefresh: Boolean, canLoadmore: Boolean) {
        super.getListResponse(data, isRefresh, canLoadmore)
        delayResult()
        mAdapter.enableLoadingMore(canLoadmore)
        if (data?.firstOrNull() is Test) {
            val result = data as List<Test>
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
        pgb_load_data.gone()
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
        mAdapter = TestAdapter(context!!)
        test_recycler_view.apply {
            setAdapter(mAdapter)
            setListLayoutManager(LinearLayoutManager.VERTICAL)
        }
    }


    private fun setUpEventRefresh() {
        test_recycler_view.setOnRefreshListener {
            getDataPage1()
        }
    }

    private fun getDataPage1() {
        test_recycler_view.enableRefresh(false)
        viewModel.apply {
            getDatamedicalHistory( true)
        }
    }

    private fun setUpEventLoadMore() {
        test_recycler_view.setOnLoadingMoreListener(object :
            EndlessLoadingRecyclerViewAdapter.OnLoadingMoreListener {
            override fun onLoadMore() {
                mAdapter.showLoadingItem(false)
                viewModel.apply {
                    getDatamedicalHistory()
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