package com.example.graduationprojectkotlin.logic.network

import com.example.graduationprojectkotlin.logic.model.StatusResponse
import com.example.graduationprojectkotlin.logic.model.TaskResponse
import retrofit2.Call
import retrofit2.http.*

interface TaskService {
    @GET("Study/task")
    fun getTasks(@Query("courseId")query:Int):Call<TaskResponse>

    @FormUrlEncoded
    @POST("Study/task?type=add")
    fun add(@Field("name") name: String, @Field("time") time: String,@Field("detail") detail: String,@Field("courseId") courseId: Int): Call<StatusResponse>


    @FormUrlEncoded
    @POST("Study/task?type=delete")
    fun delete(@Field("id") id: Int): Call<StatusResponse>

}