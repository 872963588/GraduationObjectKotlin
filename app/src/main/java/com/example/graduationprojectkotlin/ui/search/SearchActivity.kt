package com.example.graduationprojectkotlin.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationprojectkotlin.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    companion object{
        fun actionStart(context: Context) {
            val intent = Intent(context, SearchActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    val viewModel by lazy { ViewModelProvider(this).get(SearchViewModel::class.java) }

    private lateinit var adapter:CourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        //TODO 开启键盘输入位置就变是什么鬼？

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val layoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        adapter = CourseAdapter(viewModel.courseList)
        recyclerView.adapter=adapter
        et_search.addTextChangedListener { editable ->
            val content = editable.toString()
            if (content.isNotEmpty()) {
                viewModel.searchCourses(content)
            } else {
                viewModel.courseList.clear()
                adapter.notifyDataSetChanged()
            }
        }

        //viewModel.searchCourses("query")
        viewModel.courseLiveData.observe(this, Observer { result ->
            val courses = result.getOrNull()
            if (courses != null) {
                viewModel.courseList.clear()
                viewModel.courseList.addAll(courses)
                //recyclerView.adapter=adapter
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "未能查询到任何课程", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

    }
}
