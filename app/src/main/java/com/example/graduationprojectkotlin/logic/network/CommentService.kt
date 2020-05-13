package com.example.graduationprojectkotlin.logic.network

import com.example.graduationprojectkotlin.logic.model.CommentResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentService {
    @GET("Study/comment")
    fun getCourseComments(@Query("courseId")courseId:String): Call<CommentResponse>

    @GET("Study/comment")
    fun getTaskComments(@Query("taskId")taskId:String): Call<CommentResponse>
}