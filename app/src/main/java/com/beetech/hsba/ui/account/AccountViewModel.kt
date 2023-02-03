package com.beetech.hsba.ui.account

import com.beetech.hsba.base.BaseViewModel
import com.beetech.hsba.base.entity.BaseObjectResponse
import com.beetech.hsba.di.module.SharePreference
import com.beetech.hsba.extension.ObjectResponse
import com.beetech.hsba.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(var repo: Repository, var sharePre: SharePreference) :
    BaseViewModel() {
    var logout = ObjectResponse<String>()

    fun logout() {
        mDisposable.add(repo.logout().doOnSubscribe {
            logout.value = BaseObjectResponse<String>().loading()
        }.subscribe({
            sharePre.clearSharePref()
            logout.value = BaseObjectResponse<String>().success("")
        }, {
            logout.value = BaseObjectResponse<String>().error(it)
        }))
    }
}