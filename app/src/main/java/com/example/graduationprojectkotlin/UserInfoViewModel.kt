package com.example.graduationprojectkotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.User

class UserInfoViewModel : ViewModel() {

    //修改用户信息
    private val userInfoLiveData = MutableLiveData<User>()

    val statusLiveData = Transformations.switchMap(userInfoLiveData){ userInfo->
        Repository.updateUserInfo(userInfo.id.toString(),userInfo.number, userInfo.name,userInfo.email,userInfo.school,userInfo.sex)
    }

    fun setUserInfo(number: String, name: String, email: String, school: String, sex: String) {
        val id = Repository.getSavedUser().id
        userInfoLiveData.value = User(id,number, name, email, school, sex,"")
    }



    private val userIdLiveData = MutableLiveData<Int>()

    val userLiveData = Transformations.switchMap(userIdLiveData){ userId->
        Repository.getUserInfo(userId)
    }

    fun getUserInfo(userId:Int) {
        userIdLiveData.value=userId
    }
}