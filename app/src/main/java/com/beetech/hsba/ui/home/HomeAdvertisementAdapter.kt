package com.beetech.hsba.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beetech.hsba.R
import com.beetech.hsba.entity.home.Advertisement
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_advertisement.view.*

class HomeAdvertisementAdapter(
    private val mList: MutableList<Advertisement>): RecyclerView.Adapter<HomeAdvertisementAdapter.ViewHordel>() {

    inner class ViewHordel(view: View):RecyclerView.ViewHolder(view) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHordel {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_advertisement, parent, false)
        return ViewHordel(view)
    }

    override fun onBindViewHolder(holder: ViewHordel, position: Int) {
        Glide.with(holder.itemView).load(mList[position].image!!).into(holder.itemView.imv_advertisement)
    }

    override fun getItemCount(): Int = mList.size

//    private val runnable = Runnable{
//        mList.addAll(mList)
//        notifyDataSetChanged()
//    }
}