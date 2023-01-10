package com.beetech.hsba.ui.test

import androidx.lifecycle.MutableLiveData
import com.beetech.hsba.base.BaseViewModel
import com.beetech.hsba.base.entity.BaseListLoadMoreResponse
import com.beetech.hsba.entity.Test.Test
import com.beetech.hsba.extension.ListLoadMoreResponse
import com.beetech.hsba.extension.isNullOrEmpty
import com.beetech.hsba.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(var repo: Repository): BaseViewModel() {
    var data: ListLoadMoreResponse<Test> = MutableLiveData()
    fun getTokenLogin(): String{
        return repo.getDataLogin().accessToken!!
    }
    fun getDatamedicalHistory(page: String, boolean: Boolean?= false){
        mDisposable.add(
            repo.getDatamedicalHistory(page, getTokenLogin())
                .subscribe(
                    {
                        if (it.data.isNullOrEmpty()){
                            data.value = BaseListLoadMoreResponse<Test>().success(it.data, boolean!!, false)
                        }else{
                            data.value = BaseListLoadMoreResponse<Test>().success(it.data, boolean!!, true)
                        }
                    },
                    {
                        data.value = BaseListLoadMoreResponse<Test>().error(it)
                    }
                )
        )
    }
}