package com.example.graduationprojectkotlin.logic.network

import com.example.graduationprojectkotlin.logic.model.CourseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CourseService {
    @GET("Study/course")
    fun searchUser()

    //查询用户的课程 加斜杠后面人名
    @GET("Study/course")
    fun searchCourses(@Query("userId") query: String):Call<CourseResponse>

    //查询所有课程 加参数
    @GET("Study/course")
    fun searchAllCourses(@Query("userId") query: String):Call<CourseResponse>

}