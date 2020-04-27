package com.example.graduationprojectkotlin.ui.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.Comment

class CommentViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val commentList = ArrayList<Comment>()

    private val searchLiveData = MutableLiveData<String>()

    val commentLiveData = Transformations.switchMap(searchLiveData){ query->
        Repository.getComments(query)
    }

    fun searchComments(query: String) {
        searchLiveData.value = query
    }
}
