package com.example.graduationprojectkotlin.logic.network

import com.example.graduationprojectkotlin.logic.model.TaskResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TaskService {
    @GET("AppService/task")
    fun getTasks(@Query("query")query:String):Call<TaskResponse>
}