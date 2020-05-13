package com.example.graduationprojectkotlin.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.Course

class SearchViewModel: ViewModel() {

    val courseList = ArrayList<Course>()

    private val searchLiveData = MutableLiveData<String>()

    val courseLiveData = Transformations.switchMap(searchLiveData){ query->
        Repository.searchCourses(query)
    }

    fun searchCourses(query: String) {
        searchLiveData.value = query
    }
    
}