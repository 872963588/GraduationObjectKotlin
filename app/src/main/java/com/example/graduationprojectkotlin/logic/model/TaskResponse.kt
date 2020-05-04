package com.example.graduationprojectkotlin.logic.model

data class TaskResponse(val status: String, val tasks: List<Task>)

data class Task(
    val id: Int,
    val taskName: String,
    val taskTime: String,
    val taskDetail: String,
    val courseId: Int,
    val fileType : String,
    val fileUrl : String
)