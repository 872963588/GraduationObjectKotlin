package com.example.graduationprojectkotlin.ui.courses.created

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.Course

class CoursesCreatedViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val sortList = ArrayList<String>()
    var sort="全部"

    private val searchLiveData = MutableLiveData<String>()

    val courseList = ArrayList<Course>()

    val courseLiveData = Transformations.switchMap(searchLiveData){ query->
        Repository.getCreatedCourses(query)
    }

    fun searchCourses(query: String) {
        searchLiveData.value = query
    }
}
