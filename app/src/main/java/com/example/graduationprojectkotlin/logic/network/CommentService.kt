package com.example.graduationprojectkotlin.logic.network

import com.example.graduationprojectkotlin.logic.model.CommentResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentService {
    @GET("Study/comment")
    fun getComments(@Query("query")query:String): Call<CommentResponse>
}