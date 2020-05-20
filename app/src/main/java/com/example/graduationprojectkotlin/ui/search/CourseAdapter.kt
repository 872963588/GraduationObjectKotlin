package com.example.graduationprojectkotlin.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationprojectkotlin.CourseInfoActivity
import com.example.graduationprojectkotlin.GraduationProjectKotlinApplication
import com.example.graduationprojectkotlin.R
import com.example.graduationprojectkotlin.logic.model.Course

class CourseAdapter(val courseList: List<Course>) :
    RecyclerView.Adapter<CourseAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val courseName: TextView = view.findViewById(R.id.tv_course_name)
        val courseDetail: TextView = view.findViewById(R.id.tv_course_detail)
        val courseOwner: TextView = view.findViewById(R.id.tv_course_owner)
        val courseImg: ImageView = view.findViewById(R.id.iv_course_img)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.course_item, parent, false)
        val viewHolder = ViewHolder(view)
//        viewHolder.itemView.setOnClickListener {
//            CourseInfoActivity.actionStart(GraduationProjectKotlinApplication.context,1)
//        }//在onBindViewHolder里也可以写
        return viewHolder
    }

    override fun getItemCount() = courseList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = courseList[position]
        holder.courseDetail.text = course.detail
        holder.courseName.text = course.name
        holder.courseOwner.text = course.owner
        val url = course.picture
        Glide.with(GraduationProjectKotlinApplication.context).load(url).placeholder(R.drawable.img_load).into(holder.courseImg)
        holder.itemView.setOnClickListener {
            CourseInfoActivity.actionStart(GraduationProjectKotlinApplication.context,course.id)
        }
    }

}