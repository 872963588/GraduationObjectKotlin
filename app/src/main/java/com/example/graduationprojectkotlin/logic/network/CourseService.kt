package com.example.graduationprojectkotlin.logic.network

import com.example.graduationprojectkotlin.logic.model.CourseInfoResponse
import com.example.graduationprojectkotlin.logic.model.CourseResponse
import com.example.graduationprojectkotlin.logic.model.StatusResponse
import retrofit2.Call
import retrofit2.http.*

interface CourseService {
//    @GET("Study/course")
//    fun searchUser()

    @GET("Study/course")
    fun getAllCourses(@Query("all") query: String):Call<CourseResponse>

    //查询用户学习的课程 加斜杠后面人名
    @GET("Study/course")
    fun getUserCourses(@Query("userId") query: String):Call<CourseResponse>

    //获取用户创建的课程
    @GET("Study/course")
    fun getCreatedCourses(@Query("owner") query: String):Call<CourseResponse>

    //查询课程，模糊查询
    @GET("Study/course")
    fun searchCourses(@Query("name") query: String):Call<CourseResponse>

    //查询所有课程 加参数
//    @GET("Study/course")
//    fun searchAllCourses(@Query("userId") query: String):Call<CourseResponse>

    //获取课程信息
    @GET("Study/course")
    fun getCourseInfo(@Query("id") query: String):Call<CourseInfoResponse>

    //添加课程
    @FormUrlEncoded
    @POST("Study/course?type=add")
    fun createCourse(@Field("name") name: String,@Field("detail") detail: String,@Field("owner") owner: String,@Field("sort") sort: String):Call<StatusResponse>

    //删除课程
    @FormUrlEncoded
    @POST("Study/course?type=delete")
    fun deleteCourse(@Field("id") id: String):Call<StatusResponse>

}