package com.example.graduationprojectkotlin.logic.model

data class TaskResponse(val status: String, val tasks: List<Task>)

data class Task(
    val task_name: String,
    val task_time: String,
    val task_detail: String,
    val task_url: String="text"
)