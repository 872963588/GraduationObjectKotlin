package com.example.graduationprojectkotlin.ui.study.comment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.graduationprojectkotlin.R
import com.example.graduationprojectkotlin.ui.home.task.TaskViewModel
import kotlinx.android.synthetic.main.comment_fragment.*
import kotlinx.android.synthetic.main.task_fragment.*
import kotlinx.android.synthetic.main.task_fragment.recyclerView

class CommentFragment : Fragment() {

    companion object {
        fun newInstance() =
            CommentFragment()
    }

    val viewModel by lazy { ViewModelProvider(this).get(CommentViewModel::class.java) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.comment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel

        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        val adapter=CommentAdapter(this,viewModel.commentList)
        recyclerView.adapter=adapter

        viewModel.searchComments("")
        viewModel.commentLiveData.observe(viewLifecycleOwner, Observer { result ->
            val comments = result.getOrNull()
            if (comments != null) {
                viewModel.commentList.clear()
                viewModel.commentList.addAll(comments)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "未能查询到任何评论", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }

        })


    }

}
