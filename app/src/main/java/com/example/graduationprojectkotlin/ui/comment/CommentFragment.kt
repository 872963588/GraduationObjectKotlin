package com.example.graduationprojectkotlin.ui.comment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.graduationprojectkotlin.R
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
        val adapter=
            CommentAdapter(
                this,
                viewModel.commentList
            )
        recyclerView.adapter=adapter


        //TODO 判断任务还是课程
        arguments?.getInt("id")?.let { viewModel.searchComments("task",it) }
        viewModel.commentLiveData.observe(viewLifecycleOwner, Observer { result ->
            val comments = result.getOrNull()
            if (comments != null) {
                viewModel.commentList.clear()
                viewModel.commentList.addAll(comments)
                adapter.notifyDataSetChanged()
            } else {
                //TODO 这里的逻辑写一下
                //Toast.makeText(activity, "未能查询到任何评论", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }

        })


    }

}
