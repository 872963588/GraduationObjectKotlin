package com.example.graduationprojectkotlin.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.RegisterInfo
import com.example.graduationprojectkotlin.logic.model.User

class RegisterViewModel: ViewModel() {

    private val registerLiveData = MutableLiveData<RegisterInfo>()
//    var number=""
//    var name=""
//    var password=""
//    var email=""
//    var school=""
//    var sex=""

    val userLiveData = Transformations.switchMap(registerLiveData) { registerInfo ->
        Repository.register(registerInfo.number, registerInfo.name,registerInfo.password,registerInfo.email,registerInfo.school,registerInfo.sex)
    }

    fun register(number: String,name: String,password: String,email: String,school: String, sex: String) {
        registerLiveData.value = RegisterInfo(number, name, password, email, school, sex)
    }

}