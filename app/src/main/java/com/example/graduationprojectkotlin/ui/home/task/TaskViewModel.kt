package com.example.graduationprojectkotlin.ui.home.task

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.Course
import com.example.graduationprojectkotlin.logic.model.Task

class TaskViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val taskList = ArrayList<Task>()

    private val searchLiveData = MutableLiveData<String>()

    val taskLiveData = Transformations.switchMap(searchLiveData){ query->
        Repository.getTasks(query)
    }

    fun searchTasks(query: String) {
        searchLiveData.value = query
    }
}
