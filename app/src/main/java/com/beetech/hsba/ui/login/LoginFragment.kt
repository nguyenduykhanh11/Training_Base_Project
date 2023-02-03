package com.beetech.hsba.ui.login

import androidx.fragment.app.activityViewModels
import com.beetech.hsba.R
import com.beetech.hsba.base.BaseFragment
import com.beetech.hsba.base.entity.BaseError
import com.beetech.hsba.entity.Login.Data
import com.beetech.hsba.extension.Category
import com.beetech.hsba.extension.CategoryError
import com.beetech.hsba.extension.stringVal
import com.beetech.hsba.ui.homeScreen.HomeScreenFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : BaseFragment() {
    private val viewModel: LoginViewModel by activityViewModels()

    override fun backFromAddFragment() {
    }

    override val layoutId: Int
        get() = R.layout.login_fragment

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {
        btn_login?.setOnClickListener {
            hintKeyBoard()
            with(viewModel){
                login(edt_user_name!!.stringVal, edt_password!!.stringVal)
                data.observe(viewLifecycleOwner) {
                        handleObjectResponse(it)
                }
            }
        }
    }


    override fun <U> getObjectResponse(data: U) {
        if ((data is Data)) {
            getVC().replaceFragment(HomeScreenFragment::class.java)
        }
    }

    override fun handleNetworkError(throwable: Throwable?, isShowDialog: Boolean) {
        Snackbar.make(
            cl_container,
            throwable?.message.toString(),
            Snackbar.LENGTH_SHORT
        ).show()
        super.handleNetworkError(throwable, isShowDialog)
    }

    override fun handleValidateError(throwable: BaseError?) {
        Snackbar.make(cl_container, throwable!!.error, Snackbar.LENGTH_SHORT).show()
        super.handleValidateError(throwable)
    }

    override fun backPressed(): Boolean {
        return false
    }



}