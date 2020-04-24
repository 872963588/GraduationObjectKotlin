package com.example.graduationprojectkotlin.logic.model

data class CommentResponse(val status: String, val comments:List<Comment> )
data class Comment(val user_name: String, val comment_time: String,val comment_detail: String )