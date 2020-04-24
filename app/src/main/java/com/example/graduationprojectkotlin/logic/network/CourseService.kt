package com.example.graduationprojectkotlin.logic.network

import com.example.graduationprojectkotlin.logic.model.CourseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CourseService {
    @GET("AppService")
    fun searchUser()

    //查询用户的课程 加斜杠后面人名
    @GET("AppService")
    fun searchCourses(@Query("query") query: String):Call<CourseResponse>

    //查询所有课程 加参数
    @GET("AppService")
    fun searchAllCourses(@Query("query") query: String):Call<CourseResponse>

}