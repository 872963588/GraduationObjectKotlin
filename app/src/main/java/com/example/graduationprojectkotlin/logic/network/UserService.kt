package com.example.graduationprojectkotlin.logic.network

import com.example.graduationprojectkotlin.logic.model.LoginInfo
import com.example.graduationprojectkotlin.logic.model.User
import com.example.graduationprojectkotlin.logic.model.UserLoginResponse
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    //查询用户
//    @POST("Study/user")
//    fun userLogin(@Body loginInfo: LoginInfo): Call<User>

    @FormUrlEncoded
    @POST("Study/user")
    fun userLogin(@Field("userEmail") userEmail: String,@Field("userPassword") userPassword: String): Call<UserLoginResponse>

//    @GET("Study/user")
//    fun userLogin(
//        @Query("userEmail") userEmail: String,
//        @Query("userPassword") userPassword: String
//    ): Call<UserLoginResponse>
}