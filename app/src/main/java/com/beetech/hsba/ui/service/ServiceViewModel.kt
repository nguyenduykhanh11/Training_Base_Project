package com.beetech.hsba.ui.service

import androidx.lifecycle.MutableLiveData
import com.beetech.hsba.base.BaseViewModel
import com.beetech.hsba.base.entity.BaseListResponse
import com.beetech.hsba.entity.home.Services
import com.beetech.hsba.extension.ListResponse
import com.beetech.hsba.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(var repo: Repository): BaseViewModel() {
    var data: ListResponse<Services> = MutableLiveData()
    fun getDataService(){
        mDisposable.add(
            repo.getDataService()
//                .doOnSubscribe {
//                    data.value = BaseListResponse<Services>().loading()
//                }
                .subscribe(
                    {
                        data.value = BaseListResponse<Services>().success(it.data)
                    },
                    {
                        data.value = BaseListResponse<Services>().error(it)
                    }
                )
        )
    }
}