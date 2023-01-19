package com.beetech.hsba.ui.patient

import androidx.lifecycle.MutableLiveData
import com.beetech.hsba.entity.patient.ScheduleHealthCheck
import com.beetech.hsba.base.BaseViewModel
import com.beetech.hsba.base.entity.BaseListLoadMoreResponse
import com.beetech.hsba.di.module.SharePreference
import com.beetech.hsba.extension.ListLoadMoreResponse
import com.beetech.hsba.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PatientViewModel @Inject constructor(var repo: Repository, private val sharePreference: SharePreference): BaseViewModel() {
    var data: ListLoadMoreResponse<ScheduleHealthCheck> = MutableLiveData()

    private var indexPage = 1
    private fun getTokenLogin(): String{
        return sharePreference.checkSharePref().accessToken?:""
    }

    fun getScheduleHealthCheck(boolean: Boolean?= false){
        if (boolean == true) indexPage = 1
        mDisposable.add(
            repo.getScheduleHealthCheck(indexPage.toString(), getTokenLogin())
                .subscribe(
                    {
                        if (it.totalPage == indexPage) data.value = BaseListLoadMoreResponse<ScheduleHealthCheck>().success(it.data, boolean!!, false)
                        else{
                            data.value = BaseListLoadMoreResponse<ScheduleHealthCheck>().success(it.data, boolean!!, true)
                            indexPage += 1
                        }
                    },
                    {
                        data.value = BaseListLoadMoreResponse<ScheduleHealthCheck>().error(it)
                    }
                )
        )
    }
}