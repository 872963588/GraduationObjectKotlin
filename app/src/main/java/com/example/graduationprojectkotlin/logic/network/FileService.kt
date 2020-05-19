package com.example.graduationprojectkotlin.logic.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface FileService {
    @Multipart
    @POST("Study/file")
    fun uploadFile(
        @Part file: List<MultipartBody.Part>
    ): Call<ResponseBody>


    @Multipart
    @POST("Study/img")
    fun uploadImg(
        @Part file: List<MultipartBody.Part>
    ): Call<ResponseBody>
}