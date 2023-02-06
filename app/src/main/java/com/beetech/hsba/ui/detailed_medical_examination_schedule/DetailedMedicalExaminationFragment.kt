package com.beetech.hsba.ui.detailed_medical_examination_schedule

import android.util.Log
import androidx.fragment.app.activityViewModels
import com.beetech.hsba.R
import com.beetech.hsba.base.BaseFragment
import com.beetech.hsba.entity.detailedMedicalExamination.DetailedMedicalExamination
import com.beetech.hsba.extension.gone
import com.beetech.hsba.extension.visible
import com.beetech.hsba.ui.patient.PatientFragment.Companion.KEY_ID_PATIENT
import kotlinx.android.synthetic.main.fragment_detailed_medicak_examination.*
import kotlinx.android.synthetic.main.my_custom_view.*

class DetailedMedicalExaminationFragment : BaseFragment() {
    private val viewModel: DetailedMedicalExaminationViewModel by activityViewModels()
    override fun backFromAddFragment() {

    }

    override val layoutId: Int
        get() = R.layout.fragment_detailed_medicak_examination

    override fun initView() {
    }

    override fun initData() {
        arguments?.getInt(KEY_ID_PATIENT).let {
            with(viewModel) {
                getDetailedMedicalExamination(it!!)
                ListData.observe(this@DetailedMedicalExaminationFragment) {
                    handleObjectResponse(it)
                }
            }
        }
    }

    override fun <U> getObjectResponse(data: U) {
        if (data is DetailedMedicalExamination) {
            setUpView(data)
        }
        super.getObjectResponse(data)
    }

    private fun setUpView(data: DetailedMedicalExamination) {
        tv_user_name.text = data.user_name
        tv_user_address.text = "ĐC: ${data.address}"
        tv_user_phone.text = "ĐT: ${data.phone}"
        tv_department.text = data.service_specialty_name
        tv_doctor_name.text = data.doctor_name
        tv_doctor_position.text = data.position
        tv_calendar.text = data.schedule_date
        tv_time.text = "${data.schedule_time_from} - ${data.schedule_time_to}"
        tv_note.text = data.schedule_note
        if (data.medical_examination_form == OFFLINE) {
            tv_form.text = context?.getString(R.string.lable_offline)
        } else {
            tv_form.text = context?.getString(R.string.lable_online)
        }

        when (data.time_status) {
            OLD_TIME -> {
                btn_see_medical_examination_results.visible()
                btn_update_medical_examination.gone()
                btn_cancel_medical_examination.gone()
                setupViewHalder(R.string.label_detailed_medical_examination)
            }
            NEW_TIME -> {
                btn_see_medical_examination_results.gone()
                btn_update_medical_examination.visible()
                btn_cancel_medical_examination.visible()
                setupViewHalder(R.string.label_order_medical_examination)
            }
            else -> {}
        }
    }

    override fun initListener() {
        setEventBackFragment()
    }

    private fun setEventBackFragment() {
        custom_detail_medical_header.onBackClick = {
            getVC().backFromAddFragment()
        }
    }

    override fun backPressed(): Boolean {
        getVC().backFromAddFragment()
        return false
    }

    private fun setupViewHalder(title: Int) {
        with(custom_detail_medical_header) {
            setTitle(getString(title))
            imb_add.gone()
        }
    }

    companion object {
        const val OFFLINE = 0
        const val NEW_TIME = 1
        const val OLD_TIME = 0
    }
}