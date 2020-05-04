package com.example.graduationprojectkotlin.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.graduationprojectkotlin.GraduationProjectKotlinApplication
import com.example.graduationprojectkotlin.logic.model.User
import com.example.graduationprojectkotlin.logic.network.GraduationNetwork
import com.google.gson.Gson

object UserDao {

    //TODO 新用户的更新
    fun saveUser(user: User) {
        sharedPreferences().edit { putString("user", Gson().toJson(user)) }
    }

    fun getSavedUser(): User {
        val userJson = sharedPreferences().getString("user","")
        return Gson().fromJson(userJson,User::class.java)
    }

    fun isUserSaved()= sharedPreferences().contains("user")

    fun deleteUser() {
        sharedPreferences().edit().clear().commit()
    }

    private fun sharedPreferences() =
        GraduationProjectKotlinApplication.context.getSharedPreferences(
            "graduation_project",
            Context.MODE_PRIVATE
        )
}