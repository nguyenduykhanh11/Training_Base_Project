package com.beetech.hsba.ui.brief

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.beetech.hsba.R
import com.beetech.hsba.adapter.adapterBrief.SelectsOptionBloodGroupAdapter
import com.beetech.hsba.base.BaseFragment
import com.beetech.hsba.ui.homeScreen.HomeScreenFragment
import kotlinx.android.synthetic.main.fragment_brief.*
import java.text.SimpleDateFormat
import java.util.*


class BriefFragment : BaseFragment() {
    private lateinit var mAdapter: SelectsOptionBloodGroupAdapter
    override fun backFromAddFragment() {

    }

    override val layoutId: Int
        get() = R.layout.fragment_brief

    override fun initView() {
        val data = getString(R.string.lable_phone)
        masked_edt_phone.setMaskedText(data.replace(" ", ""))
        inputTextFormatDate()
    }

    override fun initData() {

    }

    fun formatTime(date: Date, format: String): String {
        val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
        return simpleDateFormat.format(date)
    }

    private fun inputTextFormatDate() {
        ll_time.setOnClickListener {
            val newCalendar: Calendar = Calendar.getInstance()
            newCalendar.time = Date()
            val startTime = DatePickerDialog(
                context!!, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                { _, year, monthOfYear, dayOfMonth ->
                    val newDate: Calendar = Calendar.getInstance()
                    newDate.set(year, monthOfYear, dayOfMonth)
                    tv_birthday.text = formatTime(newDate.time, FORMAT_TIME)
                },
                newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH)
            )
            startTime.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            startTime.show()
//            hintKeyBoard()
        }
    }


    override fun initListener() {
        setEventBackFragment()
    }

    override fun backPressed(): Boolean {
        return false
    }

    private fun setEventBackFragment() {
        my_custom_view.onBackClick = {
            (parentFragment?.parentFragment as HomeScreenFragment).backPressed()
        }
    }


    companion object {
        const val FORMAT_TIME = "dd / MM / yyyy"
    }

}