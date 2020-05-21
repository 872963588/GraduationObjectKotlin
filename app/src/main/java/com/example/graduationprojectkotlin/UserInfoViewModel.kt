package com.example.graduationprojectkotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.User
import okhttp3.MultipartBody

class UserInfoViewModel : ViewModel() {

    //修改用户信息
    var picture =Repository.getSavedUser().picture

    private val userInfoLiveData = MutableLiveData<User>()

    val statusLiveData = Transformations.switchMap(userInfoLiveData){ userInfo->
        Repository.updateUserInfo(userInfo.id.toString(),userInfo.number, userInfo.name,userInfo.email,userInfo.school,userInfo.sex,userInfo.picture)
    }

    fun setUserInfo(number: String, name: String, email: String, school: String, sex: String) {
        val id = Repository.getSavedUser().id
        userInfoLiveData.value = User(id,number, name, email, school, sex,picture)
    }



    private val userIdLiveData = MutableLiveData<Int>()

    val userLiveData = Transformations.switchMap(userIdLiveData){ userId->
        Repository.getUserInfo(userId)
    }

    fun getUserInfo(userId:Int) {
        userIdLiveData.value=userId
    }

    var parts=MutableLiveData<MutableList<MultipartBody.Part>>()
    val isUploaded=Transformations.switchMap(parts) { parts ->
        Repository.uploadImg(parts)
    }
    fun getParts(data: MutableList<MultipartBody.Part>) {
        parts.value=data
    }
}