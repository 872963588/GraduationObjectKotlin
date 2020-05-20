package com.example.graduationprojectkotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.CreateTaskInfo
import okhttp3.MultipartBody

class CreateTaskViewModel : ViewModel(){

    private val taskInfoLiveData = MutableLiveData<CreateTaskInfo>()
    val owner = Repository.getSavedUser().id.toString()//TODO 可以改为全局变量


    val statusLiveData = Transformations.switchMap(taskInfoLiveData) { taskInfo ->
        Repository.addTask(taskInfo.name,taskInfo.detail,taskInfo.courseId)
    }

    fun create(name:String,detail:String,courseId:Int) {
        //TODO 获取值改一下
       // val courseId=1
        taskInfoLiveData.value = CreateTaskInfo(name,detail,courseId)
    }


    var parts=MutableLiveData<MutableList<MultipartBody.Part>>()
    val isUploaded=Transformations.switchMap(parts) { parts ->
        Repository.uploadFile(parts)
    }
    fun getParts(data: MutableList<MultipartBody.Part>) {
        parts.value=data
    }


}