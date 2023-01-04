package com.beetech.hsba.network

import com.beetech.hsba.base.entity.BaseListLoadMoreResponse
import com.beetech.hsba.base.entity.BaseListResponse
import com.beetech.hsba.base.entity.BaseObjectResponse
import com.beetech.hsba.entity.Login.Data
import com.beetech.hsba.entity.LoginRequest
import com.beetech.hsba.entity.User
import com.beetech.hsba.entity.home.Services
import com.beetech.hsba.entity.home.Specialtys
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Repository @Inject constructor(val apiInterface: ApiInterface) {
    fun getData(pageIndex:Int): Single<BaseListLoadMoreResponse<User>> {
        return apiInterface.getDataUser("f",pageIndex)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun login(username:String,password:String): Single<BaseObjectResponse<Data>> {
        val loginRequest = LoginRequest()
        loginRequest.username = username
        loginRequest.password = password
        return apiInterface.login(loginRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDataSpecialtys(): Single<BaseListResponse<Specialtys>> {
        return apiInterface.getDataSpecialtys()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDataService(): Single<BaseListResponse<Services>> {
        return apiInterface.getDataServices()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}