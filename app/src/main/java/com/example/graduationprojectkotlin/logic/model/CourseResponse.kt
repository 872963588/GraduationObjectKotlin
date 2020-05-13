package com.example.graduationprojectkotlin.logic.model

data class CourseResponse(val status: String, val courses: List<Course>)

data class CourseInfoResponse(val status: String, val course: Course)

data class Course(
    val id: Int,
    val name: String,
    val detail: String,
    val picture: String,
    val owner: String,
    val sort:String
)

data class CreateCourseInfo(
    val name: String,
    val detail: String,
    //TODO 抓紧学呀  val picture: String,
    val owner: String,
    val sort: String
)