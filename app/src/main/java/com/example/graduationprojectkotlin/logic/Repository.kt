package com.example.graduationprojectkotlin.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.graduationprojectkotlin.logic.model.Comment
import com.example.graduationprojectkotlin.logic.model.Course
import com.example.graduationprojectkotlin.logic.model.CourseResponse
import com.example.graduationprojectkotlin.logic.model.Task
import com.example.graduationprojectkotlin.logic.network.GraduationNetwork
import kotlinx.coroutines.Dispatchers

object Repository {

    fun searchCourses(query: String)= liveData(Dispatchers.IO) {
        val result = try {
            val courseResponse = GraduationNetwork.searchCourses(query)
            if (courseResponse.status == "ok") {
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

    fun getTasks(query: String)= liveData(Dispatchers.IO) {
        val result = try {
            val taskResponse = GraduationNetwork.getTasks(query)
            if (taskResponse.status == "ok") {
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

    fun getComments(query: String)= liveData(Dispatchers.IO) {
        val result = try {
            val commentResponse = GraduationNetwork.getComments(query)
            if (commentResponse.status == "ok") {
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
}