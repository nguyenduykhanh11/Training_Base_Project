package com.beetech.hsba.adapter.adapterHome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beetech.hsba.R
import com.beetech.hsba.entity.home.SpecialtysOrService
import com.beetech.hsba.extension.URL_STORAGE
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_specialty_and_service.view.*

class HomeSpeciOrServiAdapter(): RecyclerView.Adapter<HomeSpeciOrServiAdapter.ViewHolder>() {
    private val list = mutableListOf<SpecialtysOrService>()
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<SpecialtysOrService>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_specialty_and_service, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(URL_STORAGE + list[position].icon)
            .into(holder.itemView.imv_icon)
        holder.itemView.tv_title.text = list[position].name
        if (position ==  list.size - 1) {
            holder.itemView.imv_line.setImageDrawable(null)
        }
    }

    override fun getItemCount(): Int  = list.size
}