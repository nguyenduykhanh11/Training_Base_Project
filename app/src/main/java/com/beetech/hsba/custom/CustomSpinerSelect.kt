package com.beetech.hsba.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.beetech.hsba.R
import com.beetech.hsba.adapter.adapterBrief.SelectsOptionBloodGroupAdapter
import com.beetech.hsba.extension.gone
import com.beetech.hsba.extension.visible
import kotlinx.android.synthetic.main.view_custom_spinner_select.view.*

class CustomSpinerSelect: ConstraintLayout, View.OnClickListener {
    private lateinit var mAdapter: SelectsOptionBloodGroupAdapter
    private var isExpand = false
    init {
        LayoutInflater.from(context).inflate(R.layout.view_custom_spinner_select, this, true)
    }
    constructor(context: Context) : super(context) {
        initView()
        initListener()
    }

    constructor(context: Context, attrs: AttributeSet) : super(
        context,
        attrs
    ){
        initView()
        initListener()
    }
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        initView()
        initListener()
    }

    private fun initListener() {
        ic_expand.setOnClickListener(this)
        tv_choose_status.setOnClickListener(this)
        mAdapter.refresh(BloodGroup.values().toList())
        mAdapter.onClickItem = {
            tv_choose_status.text = it.title
            rcv_spinner.gone()
            onClick(this)
        }
    }
    fun initView(){
        mAdapter = SelectsOptionBloodGroupAdapter(context)
        rcv_spinner.adapter = mAdapter
    }

    override fun onClick(p0: View?) {
        if (isExpand) {
            ic_expand.rotation = 0f
            rcv_spinner.gone()
        } else {
            ic_expand.rotation = 270f
            rcv_spinner.visible()
        }
        isExpand = !isExpand
    }

    enum class BloodGroup(val title: String) {
        A("A"), B("B"), O("O"), AB("AB")
    }

}