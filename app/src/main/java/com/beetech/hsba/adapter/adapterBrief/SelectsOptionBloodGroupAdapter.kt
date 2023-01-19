package com.beetech.hsba.adapter.adapterBrief

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beetech.hsba.R
import com.beetech.hsba.base.adapter.RecyclerViewAdapter
import com.beetech.hsba.custom.CustomSpinerSelect
import com.beetech.hsba.extension.inflate
import kotlinx.android.synthetic.main.list_item.view.*

class SelectsOptionBloodGroupAdapter(context: Context?, enableSelectedMode: Boolean = false) :
    RecyclerViewAdapter(context, enableSelectedMode) {
    var onClickItem :(CustomSpinerSelect.BloodGroup) -> Unit = {}

    override fun initNormalViewHolder(parent: ViewGroup): RecyclerView.ViewHolder? {
        return SelectOptionViewHolder(parent.inflate(R.layout.list_item))
    }

    override fun bindNormalViewHolder(holder: NormalViewHolder, position: Int) {
        val item = getItem(position, CustomSpinerSelect.BloodGroup::class.java)
        (holder as SelectOptionViewHolder).itemView.apply {
            with(tv_select_item) {
                text = item?.title
                setBackgroundResource(if (isItemSelected(position)) R.color.md_yellow_100 else android.R.color.transparent)
            }
        }
    }

    inner class SelectOptionViewHolder(view: View) : NormalViewHolder(view){
        init {
            view.setOnClickListener {
                val item = getItem(adapterPosition, CustomSpinerSelect.BloodGroup::class.java)
                item?.let{
                    onClickItem(item)
                }
            }
        }
    }
}