package com.example.graduationprojectkotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.Task

class ManageTaskViewModel  : ViewModel(){

    val taskList = ArrayList<Task>()

    private val courseLiveData = MutableLiveData<Int>()

    val taskLiveData = Transformations.switchMap(courseLiveData){ query->
        Repository.getTasks(query)
    }

    fun searchTasks(courseId: Int) {
        courseLiveData.value = courseId
    }


}