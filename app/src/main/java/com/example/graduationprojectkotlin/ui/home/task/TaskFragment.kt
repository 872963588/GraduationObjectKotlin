package com.example.graduationprojectkotlin.ui.home.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.graduationprojectkotlin.CourseInfoActivity
import com.example.graduationprojectkotlin.GraduationProjectKotlinApplication

import com.example.graduationprojectkotlin.R
import kotlinx.android.synthetic.main.task_fragment.*

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

            arguments?.getInt("courseId")?.let { it ->
                CourseInfoActivity.actionStart(GraduationProjectKotlinApplication.context,
                    it
                )
            }
        }
        //val url = "http://47.93.59.28:8080/AppService/ruanjian.jpg"
        //课程图片地址
        val url = arguments?.getString("courseImg")
        Glide.with(this).load(url).placeholder(R.drawable.img_load).into(imageView)

        //课程简介
        tv_course_name.text=arguments?.getString("courseDetail")

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

        val adapter = TaskAdapter(GraduationProjectKotlinApplication.context,viewModel.taskList,false)
        recyclerView.adapter=adapter

        arguments?.getInt("courseId")?.let { viewModel.searchTasks(it) }
        viewModel.taskLiveData.observe(viewLifecycleOwner, Observer { result ->
            val tasks = result.getOrNull()
            if (tasks != null) {
                if (tasks.isNotEmpty()) {
                    recyclerView.visibility=View.VISIBLE
                    linearLayout.visibility = View.GONE
                    viewModel.taskList.clear()
                    viewModel.taskList.addAll(tasks)
                    adapter.notifyDataSetChanged()
                } else {
                    recyclerView.visibility=View.GONE
                    linearLayout.visibility = View.VISIBLE
                    result.exceptionOrNull()?.printStackTrace()
                }
            }

        })

    }

}
