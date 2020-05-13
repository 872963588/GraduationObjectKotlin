package com.example.graduationprojectkotlin.ui.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.Comment

class CommentViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val commentList = ArrayList<Comment>()

    var type=""

    //判断获取课程还是获取任务的评论  map?

    private val searchLiveData = MutableLiveData<String>()

    val commentLiveData = Transformations.switchMap(searchLiveData){ query->
        if (this.type == "course"){
        Repository.getCourseComments(query)
        }else{
            Repository.getTaskComments(query)
        }
    }

    fun searchComments(type:String,query: Int) {
        this.type = type
        searchLiveData.value = query.toString()
    }
}
