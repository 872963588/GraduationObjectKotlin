package com.example.graduationprojectkotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.CreateCourseInfo

class CreateCourseViewModel: ViewModel() {

    private val courseInfoLiveData = MutableLiveData<CreateCourseInfo>()
    val owner = Repository.getSavedUser().id.toString()//TODO 可以改为全局变量


    val statusLiveData = Transformations.switchMap(courseInfoLiveData) { courseInfo ->
        Repository.createCourse(courseInfo.name,courseInfo.detail,courseInfo.owner,courseInfo.sort)
    }

    fun create(name:String,detail:String,sort:String) {
        courseInfoLiveData.value = CreateCourseInfo(name,detail,owner,sort)
    }

}