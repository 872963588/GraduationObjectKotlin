package com.example.graduationprojectkotlin.logic.network

import com.example.graduationprojectkotlin.logic.model.CourseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("AppService")
    fun searchUser()

    @GET("AppService")
    fun searchCourses(@Query("query") query: String):Call<CourseResponse>

}