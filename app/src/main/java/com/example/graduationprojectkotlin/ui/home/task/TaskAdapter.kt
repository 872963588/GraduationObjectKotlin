package com.example.graduationprojectkotlin.ui.home.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectkotlin.R

class TaskAdapter(val taskList: List<Task>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskImage: ImageView = view.findViewById(R.id.taskImage)
        val taskName: TextView = view.findViewById(R.id.taskName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: TaskAdapter.ViewHolder, position: Int) {
        val task = taskList[position]
        holder.taskImage.setImageResource(task.imageId)
        holder.taskName.text = task.name
    }
}