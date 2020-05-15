package com.example.graduationprojectkotlin.logic.network

import com.example.graduationprojectkotlin.logic.model.CommentResponse
import com.example.graduationprojectkotlin.logic.model.StatusResponse
import retrofit2.Call
import retrofit2.http.*

interface CommentService {
    @GET("Study/comment")
    fun getCourseComments(@Query("courseId")courseId:String): Call<CommentResponse>

    @GET("Study/comment")
    fun getTaskComments(@Query("taskId")taskId:String): Call<CommentResponse>

    @FormUrlEncoded
    @POST("Study/comment")
    fun addCourseComment(@Field("userId") userId: String, @Field("detail") detail: String, @Field("courseId") courseId: String):Call<StatusResponse>

    @FormUrlEncoded
    @POST("Study/comment")
    fun addTaskComment(@Field("userId") userId: String,@Field("detail") detail: String,@Field("taskId") courseId: String):Call<StatusResponse>

}