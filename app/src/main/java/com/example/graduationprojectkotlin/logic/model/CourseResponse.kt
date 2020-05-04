package com.example.graduationprojectkotlin.logic.model

data class CourseResponse(val status: String, val courses: List<Course>)

data class Course(
    val id: Int,
    val courseName: String,
    val courseDetail: String,
    val courseImg: String,
    val courseOwner: String
)