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
import com.example.graduationprojectkotlin.GraduationProjectKotlinApplication

import com.example.graduationprojectkotlin.R
import kotlinx.android.synthetic.main.comment_fragment.*

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

        arguments?.getInt("id")?.let { requireArguments().getString("type")?.let { it1 ->
            viewModel.searchComments(
                it1,it)
        } }
        viewModel.commentLiveData.observe(viewLifecycleOwner, Observer { result ->
            val comments = result.getOrNull()
            if (comments != null&&comments.isNotEmpty()) {
                tv_no_comment.visibility=View.GONE
                recyclerView.visibility=View.VISIBLE
                viewModel.commentList.clear()
                viewModel.commentList.addAll(comments)
                adapter.notifyDataSetChanged()
            } else {
                //TODO 这里的逻辑写一下
                recyclerView.visibility=View.GONE
                tv_no_comment.visibility=View.VISIBLE
                //Toast.makeText(activity, "未能查询到任何评论", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }

        })


        btn_add.setOnClickListener {
            viewModel.add(et_detail.text.toString(),arguments?.getInt("id").toString())
            et_detail.setText("")
        }

        viewModel.statusLiveData.observe(viewLifecycleOwner, Observer { result ->
            val statusResponse = result.getOrNull()
            if (statusResponse != null) {
                if (statusResponse.status == "true") {
                    Toast.makeText(GraduationProjectKotlinApplication.context,"发表成功", Toast.LENGTH_SHORT).show()
                    //finish()
                    //TODO 发表以后评论的刷新
                    arguments?.getInt("id")?.let { requireArguments().getString("type")?.let { it1 ->
                        viewModel.searchComments(
                            it1,it)
                    } }
                } else {
                    Toast.makeText(
                        GraduationProjectKotlinApplication.context,
                        "发表失败，请检查网络",
                        Toast.LENGTH_SHORT
                    ).show()
                    result.exceptionOrNull()?.printStackTrace()
                }
            }

        })



    }

}
