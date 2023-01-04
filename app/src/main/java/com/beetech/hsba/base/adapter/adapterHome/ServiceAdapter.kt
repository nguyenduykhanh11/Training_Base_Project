package com.beetech.hsba.base.adapter.adapterHome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beetech.hsba.R
import com.beetech.hsba.entity.home.Services
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_specialty_and_service.view.*

class ServiceAdapter(private val mList: List<Services>) : RecyclerView.Adapter<ServiceAdapter.ViewHordel>() {

    inner class ViewHordel(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHordel {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_specialty_and_service, parent, false)
        return ViewHordel(view)
    }

    override fun onBindViewHolder(holder: ViewHordel, position: Int) {
        Glide.with(holder.itemView)
            .load("http://hsba-v2.beetechdev.vn:1680/storage/"+mList[position].icon)
            .into(holder.itemView.imv_icon)
        holder.itemView.tv_title.text = mList[position].name
        if (position == mList.size -1 ){
            holder.itemView.imv_line.setImageDrawable(null)
        }
    }

    override fun getItemCount(): Int = mList.size


}