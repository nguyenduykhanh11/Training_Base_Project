package com.beetech.hsba.adapter.adapterHome

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beetech.hsba.R
import com.beetech.hsba.base.adapter.RecyclerViewAdapter
import com.beetech.hsba.entity.home.SpecialtysOrService
import com.beetech.hsba.extension.URL_STORAGE
import com.beetech.hsba.extension.inflate
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_specialty_and_service.view.*

class SpecialtyOrServiceAdapter(context: Context) :
    RecyclerViewAdapter(context, false) {
    override fun initNormalViewHolder(parent: ViewGroup): RecyclerView.ViewHolder? {
        return SpecialtyOrServiceViewHordel(parent.inflate(R.layout.item_specialty_and_service))
    }

    class SpecialtyOrServiceViewHordel(inflate: View): NormalViewHolder(inflate) {}

    override fun bindNormalViewHolder(holder: NormalViewHolder, position: Int) {
        getItem(position, SpecialtysOrService::class.java).let {
            Glide.with(holder.itemView)
                .load(URL_STORAGE + it?.icon)
                .into(holder.itemView.imv_icon)
            holder.itemView.tv_title.text = it?.name
            if (position ==  getListSize()- 1) {
                holder.itemView.imv_line.setImageDrawable(null)
            }
        }

    }


}
