package com.example.graduationprojectkotlin.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.Course

class HomeViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val searchLiveData = MutableLiveData<String>()

    val courseList = ArrayList<Course>()

    val courseLiveData = Transformations.switchMap(searchLiveData){query->
        Repository.searchCourses(query)
    }

    fun searchCourses(query: String) {
        searchLiveData.value = query
    }

}
