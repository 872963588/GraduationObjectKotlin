package com.example.graduationprojectkotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.Course

class CourseInfoViewModel : ViewModel() {

    //获取课程信息
    private val courseIdLiveData = MutableLiveData<String>()

    val courseLiveData = Transformations.switchMap(courseIdLiveData){ query->
        Repository.getCourseInfo(query)
    }

    fun getCourseInfo(query: String) {
        courseIdLiveData.value = query
    }

    //删除课程
    private val deleteIDLiveData = MutableLiveData<String>()

    val deleteLiveData = Transformations.switchMap(deleteIDLiveData){ id->
        Repository.deleteCourse(id)
    }

    fun delete() {
        deleteIDLiveData.value = courseIdLiveData.value
    }

}