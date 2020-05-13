package com.example.graduationprojectkotlin.ui.home.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectkotlin.GraduationProjectKotlinApplication
import com.example.graduationprojectkotlin.R
import com.example.graduationprojectkotlin.logic.model.Task
import com.example.graduationprojectkotlin.ui.study.ReaderActivity

class TaskAdapter(private val taskList: List<Task>) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskName: TextView = view.findViewById(R.id.tv_task_name)
        val taskTime: TextView = view.findViewById(R.id.tv_time)
        val taskDetail: TextView = view.findViewById(R.id.tv_task_detail)
        //val taskUrl: TextView = view.findViewById(R.id.tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        val viewHolder = ViewHolder(view)
//        viewHolder.itemView.setOnClickListener {
//            val position = viewHolder.adapterPosition
//            val task = taskList[position]
//            Toast.makeText(parent.context, "you clicked view ${task.name}", Toast.LENGTH_SHORT)
//                .show()
//        }
//        viewHolder.taskImage.setOnClickListener {
//            val position = viewHolder.adapterPosition
//            val task = taskList[position]
//            Toast.makeText(parent.context, "you clicked image ${task.name}", Toast.LENGTH_SHORT)
//                .show()
//        }
//        viewHolder.taskName.setOnClickListener {
//            val position = viewHolder.adapterPosition
//            val task = taskList[position]
//            Toast.makeText(parent.context, "you clicked name ${task.name}", Toast.LENGTH_SHORT)
//                .show()
//        }
        //TODO 传给StudyActivity一个值（用来判断加载什么类型的Fragment）
        viewHolder.itemView.setOnClickListener {

//                val position = viewHolder.adapterPosition
//                val intent = Intent(parent.context, Tbs1Activity::class.java)
//                    .apply { putExtra("extra_data", taskList[position].task_url) }

//                val intent = Intent(parent.context, ReaderActivity::class.java)
//                    .apply { putExtra("extra_data", "http://47.93.59.28:8080/AppService/123.docx") }
//                fragment.startActivity(intent)
            ReaderActivity.actionStart(GraduationProjectKotlinApplication.context)
            }

        return viewHolder
    }

    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = taskList[position]
        holder.taskDetail.text = task.detail
        holder.taskName.text = task.name
        holder.taskTime.text = task.time
        holder.taskName.setOnClickListener {
            Toast.makeText(GraduationProjectKotlinApplication.context,"${task.id}",Toast.LENGTH_SHORT).show()
        }
    }
}