package com.example.graduationprojectkotlin.logic.model

data class TaskResponse(val status: String, val tasks: List<Task>)

data class Task(
    val id: Int,
    val name: String,
    val time: String,
    val detail: String,
    val courseId: Int,
    val fileType : String,
    val fileUrl : String
)

data class CreateTaskInfo(
    val name: String,
    //val time: String,
    //TODO 抓紧学呀  val picture: String,
    val detail: String,
    val courseId: Int
)