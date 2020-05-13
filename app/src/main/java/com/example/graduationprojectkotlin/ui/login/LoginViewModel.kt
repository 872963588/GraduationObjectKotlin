package com.example.graduationprojectkotlin.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.LoginInfo

class LoginViewModel : ViewModel() {

    private val loginLiveData = MutableLiveData<LoginInfo>()
    val userLiveData = Transformations.switchMap(loginLiveData) { loginInfo ->
        Repository.login(loginInfo.email, loginInfo.password)
    }

    fun userLogin(email: String, password: String) {
        loginLiveData.value=LoginInfo(email,password)
    }

}

