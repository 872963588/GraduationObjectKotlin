package com.example.graduationprojectkotlin.ui.search

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationprojectkotlin.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProvider(this).get(SearchViewModel::class.java) }

    private lateinit var adapter:CourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val layoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        adapter = CourseAdapter(viewModel.courseList)
        recyclerView.adapter=adapter

        viewModel.searchcourses("query")
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
