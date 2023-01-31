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
import com.beetech.hsba.extension.gone
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

    private fun canceledItem(holder: NormalViewHolder){
        with(holder.itemView){
            ll_container.background = context!!.getDrawable(R.drawable.shape_bg_item_schedule_health)

            imv_sick.setImageDrawable(context.getDrawable(R.drawable.ic_sick_gray))
            tv_sick.apply {
                setTextColor(context.getColor(R.color.text_gray))
                typeface = null
            }

            imv_doctor.setImageDrawable(context.getDrawable(R.drawable.ic_doctor_gray))
            tv_doctor.apply {
                setTextColor(context.getColor(R.color.text_gray))
                typeface = null
            }
            imv_format.setImageDrawable(context.getDrawable(R.drawable.ic_format_gray))
            tv_format.apply {
                typeface = null
                setTextColor(context.getColor(R.color.text_gray))
            }

            imv_time.setImageDrawable(context.getDrawable(R.drawable.ic_time_gray))
            tv_time.apply {
                typeface = null
                setTextColor(context.getColor(R.color.text_gray))
            }
            imv_oval.gone()
            imv_canceled.visible()
        }
    }

    private fun oldTimeItem(holder: NormalViewHolder) {
        with(holder.itemView){
            ll_container.background = context!!.getDrawable(R.drawable.shape_bg_item_schedule_health)

            imv_sick.setImageDrawable(context.getDrawable(R.drawable.icon_sick_normal))
            tv_sick.typeface = null

            imv_doctor.setImageDrawable(context.getDrawable(R.drawable.icon_doctor_normal))
            tv_doctor.typeface = null

            imv_format.setImageDrawable(context.getDrawable(R.drawable.icon_format_normal))
            tv_format.typeface = null

            imv_time.setImageDrawable(context.getDrawable(R.drawable.icon_time_normal))
            tv_time.apply {
                typeface = null
                setTextColor(context.getColor(R.color.md_black_1000))
            }
            imv_oval.gone()
            imv_canceled.gone()
        }
    }

    private fun nextTimeItem(holder: NormalViewHolder) {
        with(holder.itemView){
            ll_container.setBackgroundColor(context!!.getColor(R.color.md_white_1000))

            imv_sick.setImageDrawable(context.getDrawable(R.drawable.icon_sick))
            tv_sick.typeface = ResourcesCompat.getFont(context, R.font.roboto_bold)

            imv_doctor.setImageDrawable(context.getDrawable(R.drawable.icon_doctor))
            tv_doctor.typeface = ResourcesCompat.getFont(context, R.font.roboto_bold)

            imv_format.setImageDrawable(context.getDrawable(R.drawable.icon_format))
            tv_format.typeface = ResourcesCompat.getFont(context, R.font.roboto_bold)

            imv_time.setImageDrawable(context.getDrawable(R.drawable.icon_time))
            tv_time.apply {
                typeface = ResourcesCompat.getFont(context, R.font.roboto_bold)
                setTextColor(context.getColor(R.color.text_color_daytime_red))
            }
            imv_oval.visible()
            imv_canceled.gone()
        }
    }

    private fun setUpData(holder: RecyclerViewAdapter.NormalViewHolder, position: Int) {
        val item = getItem(position, ScheduleHealthCheck::class.java)
        item.let {
            holder.itemView.apply {
                tv_sick.text = it?.service_specialty_name
                tv_doctor.text = it?.doctor_name
                tv_time.text = it?.format_date
                if(it?.medical_examination_form == OFFLINE){
                    tv_format.text = context.getString(R.string.lable_offline)
                }else{
                    tv_format.text = context.getString(R.string.lable_online)
                }

                when(it!!.time_status){
                    OLD_TIME -> {
                        oldTimeItem(holder)
                    }
                    NEW_TIME -> {
                        nextTimeItem(holder)
                    }
                    else -> {
                        canceledItem(holder)
                    }
                }
            }
        }
    }

    companion object{
        const val OFFLINE = 1
        const val OLD_TIME = 0
        const val NEW_TIME = 1
    }

}