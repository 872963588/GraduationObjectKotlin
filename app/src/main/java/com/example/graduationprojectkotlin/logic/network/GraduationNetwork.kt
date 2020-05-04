package com.example.graduationprojectkotlin.logic.network

import com.example.graduationprojectkotlin.logic.model.LoginInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object GraduationNetwork {


    private val taskService = ServiceCreator.create(TaskService::class.java)
    //搜索课程任务信息 TODO 添加参数
    suspend fun getTasks(query:Int) = taskService.getTasks(query).await()

    private val userService = ServiceCreator.create(UserService::class.java)
    //搜索课程任务信息 TODO 添加参数
//    suspend fun userLogin(loginInfo: LoginInfo) = userService.userLogin(loginInfo).await()
    suspend fun userLogin(userEmail: String,userPassword:String) = userService.userLogin(userEmail,userPassword).await()


    //创建UserService接口的动态代理对象
    private val courseService = ServiceCreator.create(CourseService::class.java)
    //搜索课程信息 TODO 添加参数
    suspend fun searchCourses(query: String) = courseService.searchCourses(query).await()


    //创建UserService接口的动态代理对象
    private val commentService = ServiceCreator.create(CommentService::class.java)
    //搜索课程信息 TODO 添加参数
    suspend fun getComments(query:String) = commentService.getComments(query).await()


    private suspend fun <T> Call<T>.await():T{
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body!=null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }


}