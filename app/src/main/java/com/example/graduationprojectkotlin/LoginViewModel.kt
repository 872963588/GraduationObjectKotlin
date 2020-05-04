package com.example.graduationprojectkotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.LoginInfo

class LoginViewModel : ViewModel(){
        // TODO: Implement the ViewModel

//        private val loginLiveData = MutableLiveData<LoginInfo>()
//
//        //val courseList = ArrayList<Course>()
////        val userLiveData = Transformations.switchMap(loginLiveData){ loginInfo->
////            Repository.userLogin("123465@qq.com","123465")
////        }
//
//        val userLiveData = Transformations.switchMap(loginLiveData){ loginLiveData ->
//            Repository.userLogin(loginLiveData.userEmail,loginLiveData.userPassword)
//        }
//
//        fun userLogin(loginInfo: LoginInfo) {
//            loginLiveData.value = loginInfo
//        }

}