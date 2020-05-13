package com.example.graduationprojectkotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.CreateTaskInfo

class CreateTaskViewModel : ViewModel(){

    private val taskInfoLiveData = MutableLiveData<CreateTaskInfo>()
    val owner = Repository.getSavedUser().id.toString()//TODO 可以改为全局变量


    val statusLiveData = Transformations.switchMap(taskInfoLiveData) { taskInfo ->
        Repository.addTask(taskInfo.name,taskInfo.time,taskInfo.detail,taskInfo.courseId)
    }

    fun create(name:String,time:String,detail:String) {
        //TODO 获取值改一下
        val courseId=1
        taskInfoLiveData.value = CreateTaskInfo(name,time,detail,courseId)
    }


}