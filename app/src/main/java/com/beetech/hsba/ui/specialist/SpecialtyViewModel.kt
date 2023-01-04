package com.beetech.hsba.ui.specialist

import androidx.lifecycle.MutableLiveData
import com.beetech.hsba.base.BaseViewModel
import com.beetech.hsba.base.entity.BaseListResponse
import com.beetech.hsba.entity.home.Specialtys
import com.beetech.hsba.extension.ListResponse
import com.beetech.hsba.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpecialtyViewModel @Inject constructor(var repo: Repository): BaseViewModel() {
    var data: ListResponse<Specialtys> = MutableLiveData()
    fun getDataSpecialtys(){
        mDisposable.add(
            repo.getDataSpecialtys()
//                .doOnSubscribe {
//                    data.value = BaseListResponse<Services>().loading()
//                }
                .subscribe(
                    {
                        data.value = BaseListResponse<Specialtys>().success(it.data)
                    },
                    {
                        data.value = BaseListResponse<Specialtys>().error(it)
                    }
                )
        )
    }

}