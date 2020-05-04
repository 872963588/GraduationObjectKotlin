package com.example.graduationprojectkotlin.logic.model

data class UserResponse(

    val status: String,
    val users: List<User>

)

data class UserLoginResponse(
    val status: String,
    val user: User
)

data class User(
    val id: Int,
    val userNumber: String,
    val userName: String,
    val userEmail: String,
    val userSchool: String,
    val userSex: String,
    val userImg: String

)

data class LoginInfo(

    val userEmail: String,
    val userPassword: String

)

