package com.beetech.hsba.ui.brief

import android.text.Editable
import android.text.TextWatcher
import com.beetech.hsba.R
import com.beetech.hsba.adapter.adapterBrief.SelectsOptionBloodGroupAdapter
import com.beetech.hsba.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_brief.*


class BriefFragment : BaseFragment(){
    private lateinit var mAdapter: SelectsOptionBloodGroupAdapter
    override fun backFromAddFragment() {

    }

    override val layoutId: Int
        get() = R.layout.fragment_brief

    override fun initView() {
        val data = getString(R.string.lable_phone)
        masked_edt_phone.setMaskedText(data.replace(" ",""))
        inputTextFormatDate()
    }

    override fun initData() {

    }

    private fun inputTextFormatDate() {
        edt_birthday.addTextChangedListener(object :TextWatcher{
            var sb : StringBuilder = StringBuilder("")
            var _ignore = false
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(_ignore){
                    _ignore = false
                    return
                }
                sb.clear()
                sb.append(if(p0!!.length > 14) p0.subSequence(0,14) else p0)
                if(sb.lastIndex == 2) {
                    if (sb[2].toString() != " ") sb.insert(2, " / ")
                }
                else if(sb.lastIndex == 7){
                    if(sb[7].toString() != " ") sb.insert(7," / ")
                }
                _ignore = true
                edt_birthday.apply {
                    setText(sb.toString())
                    setSelection(sb.length)
                }
            }
        })
    }

    override fun initListener() {

    }

    override fun backPressed(): Boolean {
        return false
    }

    override fun onResume() {
        edt_user_name.requestFocus()
        super.onResume()
    }

}