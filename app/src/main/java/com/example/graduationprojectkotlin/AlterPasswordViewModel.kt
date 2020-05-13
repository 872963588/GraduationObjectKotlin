package com.example.graduationprojectkotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.User

class AlterPasswordViewModel : ViewModel() {


    private val passwordLiveData = MutableLiveData<String>()
    val id = Repository.getSavedUser().id.toString()


    val statusLiveData = Transformations.switchMap(passwordLiveData){ password->
        Repository.alterPassword(id,password)
    }

    fun getPassword(password: String) {
        passwordLiveData.value = password
    }
}