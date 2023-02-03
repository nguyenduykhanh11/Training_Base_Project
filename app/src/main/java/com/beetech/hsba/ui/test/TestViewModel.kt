package com.beetech.hsba.ui.test

import androidx.lifecycle.MutableLiveData
import com.beetech.hsba.base.BaseViewModel
import com.beetech.hsba.base.entity.BaseListLoadMoreResponse
import com.beetech.hsba.entity.Test.Test
import com.beetech.hsba.extension.ListLoadMoreResponse
import com.beetech.hsba.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(var repo: Repository) : BaseViewModel() {
    var data: ListLoadMoreResponse<Test> = MutableLiveData()
    private var indexPage = 1
    fun getDatamedicalHistory(isRefresh: Boolean? = false) {
        if (isRefresh == true) indexPage = 1
        mDisposable.add(
            repo.getDatamedicalHistory(indexPage.toString())
                .subscribe(
                    {
                        if (it.totalPage == indexPage) data.value =
                            BaseListLoadMoreResponse<Test>().success(it.data, isRefresh!!, false)
                        else {
                            data.value =
                                BaseListLoadMoreResponse<Test>().success(it.data, isRefresh!!, true)
                            indexPage += 1
                        }
                    },
                    {
                        data.value = BaseListLoadMoreResponse<Test>().error(it)
                    }
                )
        )
    }
}