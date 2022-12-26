package com.beetech.hsba.ui.home

import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.beetech.hsba.R
import com.beetech.hsba.base.BaseFragment
import com.beetech.hsba.base.entity.BaseObjectResponse
import com.beetech.hsba.entity.Login.Data
import com.beetech.hsba.extension.Category
import com.beetech.hsba.extension.CategoryError
import com.beetech.hsba.extension.stringVal
import com.beetech.hsba.viewModel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.login_fragment.*
import javax.inject.Inject

class LoginFragment : BaseFragment() {
    private val viewModel: LoginViewModel by activityViewModels()

    private val btnLogin: Button? by lazy { view?.findViewById(R.id.btn_login) }
    private val edtUserName: EditText? by lazy { view?.findViewById(R.id.edt_user_name) }
    private val edtPassword: EditText? by lazy { view?.findViewById(R.id.edt_password) }
    private val clContainer: ConstraintLayout? by lazy { view?.findViewById(R.id.cl_container) }
    private var userName: String? =null
    private var password: String? =null

    companion object{
        const val NULL = ""
    }

    override fun backFromAddFragment() {

    }

    override val layoutId: Int
        get() = R.layout.login_fragment

    override fun initView() {

    }

    override fun initData() {
        viewModel.data.observe(viewLifecycleOwner) {
            handleObjectResponse(it)
        }
    }

    override fun initListener() {
        btnLogin?.setOnClickListener {
            viewModel.login(edtUserName!!.stringVal, edtPassword!!.stringVal)
        }
    }

    override fun <U> getObjectResponse(data: U) {
        if(data is Data){
            Snackbar.make(clContainer!!, Category.Successfully.CategoryError, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun handleNetworkError(throwable: Throwable?, isShowDialog: Boolean) {
        Snackbar.make(clContainer!!, catergotyError( throwable?.message.toString()), Snackbar.LENGTH_SHORT).show()
        super.handleNetworkError(throwable, isShowDialog)
    }

    private fun catergotyError(error: String): String {
        return when(error){
            getString(R.string.error_400)->{
                return getString(R.string.str_login_error_400)
            }
            getString(R.string.error_422)->{
                return getString(R.string.str_login_error_422)
            }
            else->{ NULL}
        }
    }

    override fun backPressed(): Boolean {
        return false
    }
}