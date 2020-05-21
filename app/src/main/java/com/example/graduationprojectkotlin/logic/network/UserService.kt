package com.example.graduationprojectkotlin.logic.network

import com.example.graduationprojectkotlin.logic.model.StatusResponse
import com.example.graduationprojectkotlin.logic.model.UserInfoResponse
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    //查询用户
//    @POST("Study/user")
//    fun userLogin(@Body loginInfo: LoginInfo): Call<User>

    //获取指定Id的用户信息
    @GET("Study/user")
    fun getInfo(@Query("id")id:Int):Call<UserInfoResponse>

    //登录
    @FormUrlEncoded
    @POST("Study/login")
    fun login(@Field("email") email: String, @Field("password") password: String): Call<UserInfoResponse>

    //注册
    @FormUrlEncoded
    @POST("Study/register")
    fun register(@Field("number") number: String,@Field("name") name: String,@Field("password") password: String,@Field("email") email: String,@Field("school") school: String,@Field("sex") sex: String): Call<StatusResponse>

    //更改个人信息
    @FormUrlEncoded
    @POST("Study/user?type=info")
    fun update(@Field("id") id: String,@Field("number") number: String,@Field("name") name: String,@Field("email") email: String,@Field("school") school: String,@Field("sex") sex: String): Call<StatusResponse>

    //更改密码
    @FormUrlEncoded
    @POST("Study/user?type=password")
    fun alterPassword(@Field("id") id: String,@Field("password") password: String): Call<StatusResponse>

    //加入课程
    @FormUrlEncoded
    @POST("Study/user?type=add")
    fun addUserCourse(@Field("userId") userId: Int,@Field("courseId") courseId: Int): Call<StatusResponse>

    //退出课程
    @FormUrlEncoded
    @POST("Study/user?type=del")
    fun delUserCourse(@Field("userId") userId: Int,@Field("courseId") courseId: Int): Call<StatusResponse>

//    @GET("Study/user")
//    fun userLogin(
//        @Query("userEmail") userEmail: String,
//        @Query("userPassword") userPassword: String
//    ): Call<UserLoginResponse>
}