package com.example.graduationprojectkotlin.ui.home.task

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.graduationprojectkotlin.CourseInfoActivity
import com.example.graduationprojectkotlin.GraduationProjectKotlinApplication

import com.example.graduationprojectkotlin.R
import com.example.graduationprojectkotlin.logic.model.Task
import com.example.graduationprojectkotlin.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.task_fragment.*
import retrofit2.http.Url
import java.net.URL

class TaskFragment (): Fragment() {

    companion object {
        fun newInstance() = TaskFragment()
    }
    //TODO:这里有接口

    val viewModel by lazy { ViewModelProvider(this).get(TaskViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.task_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        course_info_layout.setOnClickListener{
//            //TODO why此处上下文可能为空
//            context?.let { it1 -> CourseInfoActivity.actionStart(it1,"课程名") }

            CourseInfoActivity.actionStart(GraduationProjectKotlinApplication.context,"课程名")
        }
        val url = "http://47.93.59.28:8080/AppService/ruanjian.jpg"
        Glide.with(this).load(url).into(imageView)

//        //TODO 接收BUNDLE
//        //TODO 获取课程名，为空的话怎么办？
//        val course = arguments?.getInt("course")
//
//        //TODO 根据课程名 加载数据
//        val adapter =  when(course){
//            0->TaskAdapter(this,taskList1)
//            1->TaskAdapter(this,taskList2)
//            else-> TaskAdapter(this,taskList3)
//        }
//       // val adapter = TaskAdapter(taskList1)
//        recyclerView.adapter=adapter
        val adapter = TaskAdapter(this,viewModel.taskList)
        recyclerView.adapter=adapter

        viewModel.searchTasks("")
        viewModel.taskLiveData.observe(viewLifecycleOwner, Observer { result ->
            val tasks = result.getOrNull()
            if (tasks != null) {
                viewModel.taskList.clear()
                viewModel.taskList.addAll(tasks)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "未能查询到任何任务", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }

        })

    }

}
