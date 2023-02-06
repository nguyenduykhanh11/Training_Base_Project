package com.beetech.hsba.ui.account

import androidx.fragment.app.activityViewModels
import com.beetech.hsba.R
import com.beetech.hsba.base.BaseFragment
import com.beetech.hsba.extension.gone
import com.beetech.hsba.ui.homeScreen.HomeScreenFragment
import com.beetech.hsba.ui.login.LoginFragment
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.my_custom_view.*

class AccountFragment : BaseFragment() {
    private val viewModel: AccountViewModel by activityViewModels()
    override fun backFromAddFragment() {

    }

    override val layoutId: Int
        get() = R.layout.fragment_account

    override fun initView() {
        setUpViewHeader()
    }

    private fun setUpViewHeader() {
        imb_add.gone()
        tv_title.gone()
    }

    override fun initData() {

    }

    override fun initListener() {
        setUpListenerClickLogout()
        setEventBackFragment()
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

    private fun setEventBackFragment() {
        custom_header_account.onBackClick = {
            (parentFragment?.parentFragment as HomeScreenFragment).backPressed()
        }
    }

    override fun <U> getObjectResponse(data: U) {
        (parentFragment?.parentFragment as BaseFragment).getVC()
            .replaceFragment(LoginFragment::class.java)
    }

    override fun backPressed(): Boolean {
        return false
    }
}