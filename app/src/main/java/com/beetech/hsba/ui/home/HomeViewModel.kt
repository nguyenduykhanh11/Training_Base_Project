package com.beetech.hsba.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beetech.hsba.base.BaseViewModel
import com.beetech.hsba.base.entity.BaseListResponse
import com.beetech.hsba.entity.home.SpecialtysOrService
import com.beetech.hsba.extension.ListResponse
import com.beetech.hsba.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(var repo: Repository): BaseViewModel() {
    var dataSpecialty: ListResponse<SpecialtysOrService> = MutableLiveData()
    var dataService: ListResponse<SpecialtysOrService> = MutableLiveData()

    fun getDataSpecialtys(){
        mDisposable.add(
            repo.getDataSpecialtys()
                .subscribe(
                    {
                        dataSpecialty.value = BaseListResponse<SpecialtysOrService>().success(it.data)
                    },
                    {
                        dataSpecialty.value = BaseListResponse<SpecialtysOrService>().error(it)
                    }
                )
        )
    }

    fun getDataService(){
        mDisposable.add(
            repo.getDataService()
                .subscribe(
                    {
                        dataService.value = BaseListResponse<SpecialtysOrService>().success(it.data)
                    },
                    {
                        dataService.value = BaseListResponse<SpecialtysOrService>().error(it)
                    }
                )
        )
    }
}