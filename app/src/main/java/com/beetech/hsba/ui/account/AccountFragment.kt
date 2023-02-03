package com.beetech.hsba.ui.account

import androidx.fragment.app.activityViewModels
import com.beetech.hsba.R
import com.beetech.hsba.base.BaseFragment
import com.beetech.hsba.ui.login.LoginFragment
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : BaseFragment() {
    private val viewModel: AccountViewModel by activityViewModels()
    override fun backFromAddFragment() {

    }

    override val layoutId: Int
        get() = R.layout.fragment_account

    override fun initView() {
    }

    override fun initData() {

    }

    override fun initListener() {
        setUpListenerClickLogout()
    }

    private fun setUpListenerClickLogout() {
        btn_logout.setOnClickListener {
            with(viewModel) {
                logout()
                logout.observe(this@AccountFragment) {
                    handleObjectResponse(it)
                }
            }
        }
    }

    override fun <U> getObjectResponse(data: U) {
        getVC().replaceFragment(LoginFragment::class.java)
    }

    override fun backPressed(): Boolean {
        return false
    }
}