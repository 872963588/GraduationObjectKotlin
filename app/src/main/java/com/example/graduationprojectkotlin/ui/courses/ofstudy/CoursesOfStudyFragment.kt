package com.example.graduationprojectkotlin.ui.courses.ofstudy

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationprojectkotlin.GraduationProjectKotlinApplication

import com.example.graduationprojectkotlin.R
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.ui.search.CourseAdapter
import kotlinx.android.synthetic.main.courses_of_study_fragment.*

class CoursesOfStudyFragment : Fragment() {

    companion object {
        fun newInstance() = CoursesOfStudyFragment()
    }

    private lateinit var viewModel: CoursesOfStudyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.courses_of_study_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(CoursesOfStudyViewModel::class.java)
        // TODO: Use the ViewModel
        val listAdapter = ArrayAdapter<String>(GraduationProjectKotlinApplication.context,android.R.layout.simple_list_item_1,viewModel.sortList)
        listView.adapter = listAdapter
        listView.setSelector(R.color.colorAccent)
        listView.setOnItemClickListener {parent,view,position,id->

            viewModel.sort=viewModel.sortList[position]
            viewModel.searchCourses(Repository.getSavedUser().id.toString())
        }

        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        val recyclerAdapter = CourseAdapter(viewModel.courseList)
        recyclerView.adapter=recyclerAdapter

        viewModel.searchCourses(Repository.getSavedUser().id.toString())
        viewModel.courseLiveData.observe(viewLifecycleOwner, Observer { result ->
            val courses = result.getOrNull()
            if (courses != null) {
                if (courses.isNotEmpty()) {
//TODO 两个if可以合并
                    //课程的更新
                    viewModel.courseList.clear()
                    when(viewModel.sort){
                        "全部"->viewModel.courseList.addAll(courses)
                        else->{for (course in courses){
                            if(course.sort==viewModel.sort)
                                viewModel.courseList.add(course)
                        }
                        }
                    }
                    recyclerAdapter.notifyDataSetChanged()

                    //课程分类的更新
                    viewModel.sortList.clear()
                    viewModel.sortList.add("全部")
                    for (course in courses) {
                        if(!viewModel.sortList.contains(course.sort)){
                            viewModel.sortList.add(course.sort)

                        }
                    }
                    listAdapter.notifyDataSetChanged()
                    tv_no_course.visibility=View.GONE
                    linearLayout.visibility=View.VISIBLE


                } else {
                    linearLayout.visibility=View.GONE
                    tv_no_course.visibility=View.VISIBLE
                    //Toast.makeText(activity, "未能查询到任何课程", Toast.LENGTH_SHORT).show()
                    result.exceptionOrNull()?.printStackTrace()
                }
            }

        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.searchCourses(Repository.getSavedUser().id.toString())

    }

}
