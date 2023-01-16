package com.beetech.hsba.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.beetech.hsba.R
import kotlinx.android.synthetic.main.layout_base_recyclerview.view.*
import kotlinx.android.synthetic.main.my_custom_view.view.*

class MyCustomView: ConstraintLayout {

    init {
        LayoutInflater.from(context).inflate(R.layout.my_custom_view, this, true)
    }

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(
        context,
        attrs
    )
    fun setTitle(title: String) {
        tv_title.text = title
    }

    fun setBackStack(){

    }

}