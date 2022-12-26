package com.beetech.hsba.network


import com.beetech.hsba.base.entity.BaseListLoadMoreResponse
import com.beetech.hsba.base.entity.BaseObjectResponse
import com.beetech.hsba.entity.Login.Data
import com.beetech.hsba.entity.LoginRequest
import com.beetech.hsba.entity.User
import io.reactivex.Single
import retrofit2.http.*

interface ApiInterface {

    @GET("search1")
    @Headers("Content-Type: application/json", "lang: vi")
    fun getDataUser(
        @Query("s") keyWord: String,
        @Query("page") page: Int
    ): Single<BaseListLoadMoreResponse<User>>

//    @POST("/api/auth/login/customer")
//    @Headers("Content-Type: application/json")
//    fun login(@Body loginRequest:LoginRequest) : Single<BaseObjectResponse<LoginResponse>>

    @POST("user/login")
    @Headers("Content-Type: application/json", "version: 2.0.0", "device: 1")
    fun login(@Body loginRequest:LoginRequest) : Single<BaseObjectResponse<Data>>
}