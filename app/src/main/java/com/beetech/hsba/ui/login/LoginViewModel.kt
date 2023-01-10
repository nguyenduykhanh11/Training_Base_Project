package com.beetech.hsba.ui.login

import android.content.Context
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.beetech.hsba.BaseApplication.Companion.context
import com.beetech.hsba.R
import com.beetech.hsba.base.BaseViewModel
import com.beetech.hsba.base.entity.BaseError
import com.beetech.hsba.base.entity.BaseObjectResponse
import com.beetech.hsba.entity.Login.Data
import com.beetech.hsba.extension.CategotySharePref
import com.beetech.hsba.extension.ObjectResponse
import com.beetech.hsba.extension.SharePref
import com.beetech.hsba.network.Repository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var repo: Repository): BaseViewModel() {
    var data: ObjectResponse<Data> = MutableLiveData()
    fun login(userName: String, password: String) {
        if (checkValidate(userName, password)){
            mDisposable.add(
                repo.login(userName,password)
                    .doOnSubscribe {
                        data.value = BaseObjectResponse<Data>().loading()
                    }
                    .subscribe(
                        {
                            saveInfoLogin(it.data!!)
                            data.value = BaseObjectResponse<Data>().success(it.data)
                        },
                        {
                            data.value = BaseObjectResponse<Data>().error(it)
                        }
                    )
            )
        }
    }

    private fun saveInfoLogin(data: Data) {
        context.getSharedPreferences(SharePref.MyPref.CategotySharePref, Context.MODE_PRIVATE).let {
            it.edit().apply{
                putString(SharePref.MyPref.CategotySharePref, Gson().toJson(data))
                apply()
            }
        }
    }


    private fun checkValidate(userName: String, password: String): Boolean {
        if(TextUtils.isEmpty(userName)&&TextUtils.isEmpty(password)){
            data.value = BaseObjectResponse<Data>().error(
                BaseError(context.getString(R.string.str_empty_all)),
                false
            )
            return false
        }
        else{
            if (TextUtils.isEmpty(userName)){
                data.value = BaseObjectResponse<Data>().error(
                    BaseError(context.getString(R.string.str_login_validate_user_name)),
                    false
                )
                return false
            }
            if (TextUtils.isEmpty(password)){
                data.value = BaseObjectResponse<Data>().error(
                    BaseError(context.getString(R.string.str_empty_password)),
                    false
                )
                return false
            }
            return true
        }
    }
}