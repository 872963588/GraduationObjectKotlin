package com.example.graduationprojectkotlin.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectkotlin.R
import com.example.graduationprojectkotlin.logic.model.Course

class CourseAdapter(val courseList: List<Course>) :
    RecyclerView.Adapter<CourseAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val courseName: TextView = view.findViewById(R.id.tv_course_name)
        val courseDetail: TextView = view.findViewById(R.id.tv_course_detail)
        val courseOwner: TextView = view.findViewById(R.id.tv_course_owner)
        //val courseUrl: TextView = view.findViewById(R.id.tv)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.course_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = courseList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = courseList[position]
        holder.courseDetail.text = course.course_detail
        holder.courseName.text = course.course_name
        holder.courseOwner.text = course.course_owner
    }

}