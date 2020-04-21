package com.example.graduationprojectkotlin.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
 * Retrofit构建器，获取Service接口的动态代理对象
 */

object ServiceCreator {
    //服务器地址，Retrofit根路径 TODO 最后面的/
    private const val BASE_URL = "http://122.51.224.44/"

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    //泛型实化
    inline fun <reified T> create(): T = create(T::class.java)

}