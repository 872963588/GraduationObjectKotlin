package com.example.graduationprojectkotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.CreateCourseInfo
import okhttp3.MultipartBody

class CreateCourseViewModel : ViewModel() {

    private val courseInfoLiveData = MutableLiveData<CreateCourseInfo>()
    private val owner = Repository.getSavedUser().id.toString()


    val statusLiveData = Transformations.switchMap(courseInfoLiveData) { courseInfo ->
        Repository.createCourse(
            courseInfo.name,
            courseInfo.detail,
            courseInfo.owner,
            courseInfo.sort
        )
    }

    fun create(name: String, detail: String, sort: String) {
        courseInfoLiveData.value = CreateCourseInfo(name, detail, owner, sort)
    }

    var parts = MutableLiveData<MutableList<MultipartBody.Part>>()
    val isUploaded = Transformations.switchMap(parts) { parts ->
        Repository.uploadImg(parts)
    }

    fun getParts(data: MutableList<MultipartBody.Part>) {
        parts.value = data
    }

}