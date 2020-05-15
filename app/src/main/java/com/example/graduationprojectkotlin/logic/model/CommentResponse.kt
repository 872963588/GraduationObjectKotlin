package com.example.graduationprojectkotlin.logic.model

data class CommentResponse(val status: String, val comments: List<Comment>)
data class Comment(
    val id: Int,
    val userId: Int,
    val time: String,
    val detail: String,
    val name: String,
    val picture: String)

data class AddCourseCommentInfo(
    val userId: Int,
    val detail: String,
    val courseOrTaskId: String)

data class AddTaskCommentInfo(
    val taskId: Int,
    val detail: String,
    val courseOrTaskId: String)