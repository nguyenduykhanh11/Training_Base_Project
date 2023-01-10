package com.beetech.hsba.base.adapter.adapterTest

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beetech.hsba.R
import com.beetech.hsba.base.adapter.EndlessLoadingRecyclerViewAdapter
import com.beetech.hsba.base.adapter.RecyclerViewAdapter
import com.beetech.hsba.entity.Test.Test
import com.beetech.hsba.extension.URL_STORAGE
import com.beetech.hsba.extension.gone
import com.beetech.hsba.extension.inflate
import com.beetech.hsba.extension.visible
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_test.view.*

class TestAdapter(context: Context): EndlessLoadingRecyclerViewAdapter(context = context, false) {

    override fun initNormalViewHolder(parent: ViewGroup): RecyclerView.ViewHolder? {
        return ListTestViewHordel(parent.inflate(R.layout.item_test))
    }
    inner class ListTestViewHordel(inflate: View): NormalViewHolder(inflate)

    override fun bindNormalViewHolder(holder: NormalViewHolder, position: Int) {
        setUpView(holder, position)
        setOnclickItem(holder)
    }

    private fun setUpView(holder: RecyclerViewAdapter.NormalViewHolder, position: Int) {
        val ths =context?.getString(R.string.text_ThS)
        val ts =context?.getString(R.string.text_TS)
        val item = getItem(position, Test::class.java)
        item.let {
            holder.itemView.apply {
                val name = it?.doctor_name
                tv_day_time.text = it?.date_medical_histories
                tv_title_test.text = it?.service_specialty_name
                Glide.with(this)
                    .load(URL_STORAGE +it?.avatar)
                    .into(imv_avatar_doctor)
                imv_show.visible()
                ll_form_delete_item.gone()
                if(name!!.contains(ts!!)){
                    tv_name_doctor.text = name.substring(4)
                    tv_degree.text = context.getString(R.string.settext_tien_si)
                }
                else if(name.contains(ths!!)){
                    tv_name_doctor.text = name.substring(5)
                    tv_degree.text = context.getString(R.string.settext_thac_si)
                }else{
                    tv_name_doctor.text = it.doctor_name
                    tv_degree.text = null
                }
            }
        }
    }

    private fun setOnclickItem(holder: RecyclerViewAdapter.NormalViewHolder) {
        with(holder.itemView){
            imv_show.setOnClickListener {
                imv_show.gone()
                ll_form_delete_item.visible()
            }
            imv_back.setOnClickListener {
                imv_show.visible()
                ll_form_delete_item.gone()
            }

        }
    }
}