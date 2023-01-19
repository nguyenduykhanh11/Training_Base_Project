package com.beetech.hsba.adapter.adapterScheduleHealthCheck

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.beetech.hsba.R
import com.beetech.hsba.base.adapter.EndlessLoadingRecyclerViewAdapter
import com.beetech.hsba.base.adapter.RecyclerViewAdapter
import com.beetech.hsba.entity.patient.ScheduleHealthCheck
import com.beetech.hsba.extension.inflate
import com.beetech.hsba.extension.visible
import kotlinx.android.synthetic.main.item_schedule_health_check.view.*


class ScheduleHealthCheckAdapter(context: Context): EndlessLoadingRecyclerViewAdapter(context = context, false) {
    override fun initNormalViewHolder(parent: ViewGroup): RecyclerView.ViewHolder? {
        return ListTestViewHordel(parent.inflate(R.layout.item_schedule_health_check))
    }
    inner class ListTestViewHordel(inflate: View): NormalViewHolder(inflate)

    override fun bindNormalViewHolder(holder: NormalViewHolder, position: Int) {
        setUpData(holder, position)

    }


    private fun selectItem(holder: NormalViewHolder) {
        with(holder.itemView){
            ll_container.setBackgroundColor(context!!.getColor(R.color.md_white_1000))
            tv_sick.apply {
                compoundDrawableTintMode = null
                typeface = ResourcesCompat.getFont(context, R.font.roboto_bold)
            }
            tv_doctor.apply {
                compoundDrawableTintMode = null
                typeface = ResourcesCompat.getFont(context, R.font.roboto_bold)
            }
            tv_format.apply {
                compoundDrawableTintMode = null
                typeface = ResourcesCompat.getFont(context, R.font.roboto_bold)
            }
            tv_time.apply {
                compoundDrawableTintMode = null
                typeface = ResourcesCompat.getFont(context, R.font.roboto_bold)
                setTextColor(context.getColor(R.color.text_color_daytime_red))
            }
            imv_oval.visible()
        }
    }



    private fun setUpData(holder: RecyclerViewAdapter.NormalViewHolder, position: Int) {
        val item = getItem(position, ScheduleHealthCheck::class.java)
        item.let {
            holder.itemView.apply {
                tv_sick.text = it?.service_specialty_name
                tv_doctor.text = it?.doctor_name
                tv_format.text = it?.medical_examination_form.toString()
                tv_time.text = it?.format_date
            }
        }
    }

}