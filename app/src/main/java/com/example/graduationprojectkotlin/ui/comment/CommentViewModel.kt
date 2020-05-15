package com.example.graduationprojectkotlin.ui.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.AddCourseCommentInfo
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

//TODO 添加课程评论
    private val commentInfoLiveData = MutableLiveData<AddCourseCommentInfo>()
    val userId = Repository.getSavedUser().id//TODO 可以改为全局变量


    val statusLiveData = Transformations.switchMap(commentInfoLiveData) { comment ->
        if (this.type == "course"){
            Repository.addCourseComment(comment.userId.toString(),comment.detail,comment.courseOrTaskId)
        }else{
            Repository.addTaskComment(comment.userId.toString(),comment.detail,comment.courseOrTaskId)
        }
    }

    fun add(detail:String,courseOrTaskId:String) {
        commentInfoLiveData.value = AddCourseCommentInfo(userId,detail,courseOrTaskId)
    }


    //TODO 添加任务评论
}
