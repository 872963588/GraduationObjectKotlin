package com.example.graduationprojectkotlin.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.example.graduationprojectkotlin.logic.dao.UserDao
import com.example.graduationprojectkotlin.logic.model.*
import com.example.graduationprojectkotlin.logic.network.GraduationNetwork
import com.example.graduationprojectkotlin.logic.network.ServiceCreator
import com.example.graduationprojectkotlin.logic.network.ServiceCreator.create
import com.example.graduationprojectkotlin.logic.network.UserService
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response

object Repository {

    //    fun userLogin(userEmail: String, userPassword: String)= liveData(Dispatchers.IO) {
//        val result = try {
//            val userLoginResponse = GraduationNetwork.userLogin(userEmail,userPassword)
//            //判断是否得到数据
//            if (userLoginResponse.status == "true") {
//                Result.success(userLoginResponse)
//            } else {
//                //TODO 没有获取到数据或请求失败提示
//                Result.failure(RuntimeException("response status is ?"))
//            }
//        } catch (e: Exception) {
//            Result.failure<UserLoginResponse>(e)
//        }
//        //TODO as需要么？
//        emit(result as Result<UserLoginResponse>)
//    }
    fun userLogin(userEmail: String, userPassword: String) {
        val userService = ServiceCreator.create(UserService::class.java)
        userService.userLogin(userEmail, userPassword)
            .enqueue(object : Callback<UserLoginResponse> {
                override fun onFailure(call: Call<UserLoginResponse>, t: Throwable) {
                    Log.d("123465", "失败1")
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<UserLoginResponse>,
                    response: Response<UserLoginResponse>
                ) {
                    val userLoginResponse = response.body()
                    if (userLoginResponse != null) {
                        if (userLoginResponse.status=="true") {
                            //TODO 要把值传出去
                            saveUser(userLoginResponse.user)
                            Log.d("123465", "成功")
                        } else {
                            Log.d("123465", "失败2")
                        }
                    }
                }

            })
    }

    fun searchCourses(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val courseResponse = GraduationNetwork.searchCourses(query)
            if (courseResponse.status == "true") {
                val courses = courseResponse.courses
                Result.success(courses)
            } else {
                Result.failure(RuntimeException("response status is ${courseResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Course>>(e)
        }
        //TODO as需要么？
        emit(result as Result<List<Course>>)
    }

    //fun userLogin(data:UserLoginInfo):

    //TODO 获取课程任务
    fun getTasks(query: Int) = liveData(Dispatchers.IO) {
        val result = try {
            val taskResponse = GraduationNetwork.getTasks(query)
            if (taskResponse.status == "true") {
                val tasks = taskResponse.tasks
                Result.success(tasks)
            } else {
                Result.failure(RuntimeException("response status is ${taskResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Task>>(e)
        }
        //TODO as需要么？
        emit(result as Result<List<Task>>)
    }

    //TODO 获取课程或任务的评论
    fun getComments(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val commentResponse = GraduationNetwork.getComments(query)
            if (commentResponse.status == "true") {
                val comments = commentResponse.comments
                Result.success(comments)
            } else {
                Result.failure(RuntimeException("response status is ${commentResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<com.example.graduationprojectkotlin.logic.model.Comment>>(e)
        }
        //TODO as需要么？
        emit(result as Result<List<Comment>>)
    }

    fun saveUser(user: User) =UserDao.saveUser(user)

    fun getSavedUser()=UserDao.getSavedUser()

    fun isUserSaved()=UserDao.isUserSaved()

    fun deleteUser()=UserDao.deleteUser()
}