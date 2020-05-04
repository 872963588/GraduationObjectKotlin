package com.example.graduationprojectkotlin.logic.model

data class CommentResponse(val status: String, val comments: List<Comment>)
data class Comment(
    val id: Int,
    val userId: Int,
    val commentTime: String,
    val commentDetail: String,
    val courseId: String,
    val taskId: String)