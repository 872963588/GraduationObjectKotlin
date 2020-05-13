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
    val number: String,
    val name: String,
    val email: String,
    val school: String,
    val sex: String,
    val picture: String

)

data class LoginInfo(

    val email: String,
    val password: String

)

data class RegisterInfo(
   // val id: Int,
    val number: String,
    val name: String,
    val password: String,
    val email: String,
    val school: String,
    val sex: String
    //val picture: String

)

data class AlertUserInfo(
    // val id: Int,
    val number: String,
    val name: String,
    val password: String,
    val email: String,
    val school: String,
    val sex: String
    //val picture: String

)
