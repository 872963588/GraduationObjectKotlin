package com.example.graduationprojectkotlin.logic.model

data class CourseResponse(val status: String, val courses: List<Course>)

data class Course(
    val course_name: String,
    val course_task: String,
    val course_img: String,
    val course_detail: String,
    val course_owner: String
)