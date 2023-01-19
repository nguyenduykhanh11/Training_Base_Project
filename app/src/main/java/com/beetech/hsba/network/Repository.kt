package com.beetech.hsba.network

import com.beetech.hsba.entity.patient.ScheduleHealthCheck
import com.beetech.hsba.base.entity.BaseListLoadMoreResponse
import com.beetech.hsba.base.entity.BaseListResponse
import com.beetech.hsba.base.entity.BaseObjectResponse
import com.beetech.hsba.entity.Login.Data
import com.beetech.hsba.entity.LoginRequest
import com.beetech.hsba.entity.Test.Test
import com.beetech.hsba.entity.User
import com.beetech.hsba.entity.home.SpecialtysOrService
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

    fun getDataSpecialtys(): Single<BaseListResponse<SpecialtysOrService>> {
        return apiInterface.getDataSpecialtys()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDataService(): Single<BaseListResponse<SpecialtysOrService>> {
        return apiInterface.getDataServices()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDatamedicalHistory(page: String, token: String): Single<BaseListLoadMoreResponse<Test>> {
        return apiInterface.getDatamedicalHistory( page, "Bearer $token")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getScheduleHealthCheck(page: String, token: String): Single<BaseListLoadMoreResponse<ScheduleHealthCheck>> {
        return apiInterface.getDataScheduleHealthCheck( page, "Bearer $token")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}