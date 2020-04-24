package com.example.graduationprojectkotlin.ui.course

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.Course

class SearchCourseViewModel: ViewModel() {

    val courseList = ArrayList<Course>()

    private val searchLiveData = MutableLiveData<String>()

    val courseLiveData = Transformations.switchMap(searchLiveData){ query->
        Repository.searchCourses(query)
    }

    fun searchcourses(query: String) {
        searchLiveData.value = query
    }
    
}