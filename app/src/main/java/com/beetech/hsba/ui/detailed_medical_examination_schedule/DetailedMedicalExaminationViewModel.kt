package com.beetech.hsba.ui.detailed_medical_examination_schedule

import com.beetech.hsba.base.BaseViewModel
import com.beetech.hsba.base.entity.BaseObjectResponse
import com.beetech.hsba.entity.detailedMedicalExamination.DetailedMedicalExamination
import com.beetech.hsba.extension.ObjectResponse
import com.beetech.hsba.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailedMedicalExaminationViewModel @Inject constructor(var repo: Repository) :
    BaseViewModel() {
    var ListData = ObjectResponse<DetailedMedicalExamination>()

    fun getDetailedMedicalExamination(id: Int) {
        mDisposable.add(
            repo.getDetailedMedicalExamination(id)
                .doOnSubscribe {
                    ListData.value = BaseObjectResponse<DetailedMedicalExamination>().loading()
                }
                .subscribe(
                    {
                        ListData.value =
                            BaseObjectResponse<DetailedMedicalExamination>().success(it.data!!)
                    },
                    {
                        ListData.value = BaseObjectResponse<DetailedMedicalExamination>().error(it)
                    }
                )
        )
    }
}