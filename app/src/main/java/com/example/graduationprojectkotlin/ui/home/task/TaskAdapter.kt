package com.example.graduationprojectkotlin.ui.home.task

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectkotlin.GraduationProjectKotlinApplication
import com.example.graduationprojectkotlin.R
import com.example.graduationprojectkotlin.TaskInfoActivity
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.Task
import com.example.graduationprojectkotlin.ui.study.ReaderActivity

class TaskAdapter(val context:Context,private val taskList: List<Task>,val isManage:Boolean) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskName: TextView = view.findViewById(R.id.tv_course_name)
        val taskTime: TextView = view.findViewById(R.id.tv_course_detail)
        val taskDetail: TextView = view.findViewById(R.id.tv_course_owner)
        //val taskUrl: TextView = view.findViewById(R.id.tv)
        val imgBtn: ImageButton=view.findViewById(R.id.imgBtn_delete)
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
        if (isManage){
            viewHolder.imgBtn.visibility = View.VISIBLE
        }


//        viewHolder.itemView.setOnClickListener {
//
////                val position = viewHolder.adapterPosition
////                val intent = Intent(parent.context, Tbs1Activity::class.java)
////                    .apply { putExtra("extra_data", taskList[position].task_url) }
//
////                val intent = Intent(parent.context, ReaderActivity::class.java)
////                    .apply { putExtra("extra_data", "http://47.93.59.28:8080/AppService/123.docx") }
////                fragment.startActivity(intent)
//            TaskInfoActivity.actionStart(GraduationProjectKotlinApplication.context)
//            }

        return viewHolder
    }

    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = taskList[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(GraduationProjectKotlinApplication.context, TaskInfoActivity::class.java)
                    .apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK

                        putExtra("id",task.id)
                        putExtra("fileType",task.fileType)

                        putExtra("time",task.time)
                        putExtra("name",task.name)
                        putExtra("detail",task.detail)
                        putExtra("fileUrl",task.fileUrl)
                    }
            startActivity(context,intent,null)
        }


        holder.taskDetail.text = task.detail
        holder.taskName.text = task.name
        holder.taskTime.text = task.time

//        holder.taskName.setOnClickListener {
//            Toast.makeText(GraduationProjectKotlinApplication.context,"",Toast.LENGTH_SHORT).show()
//        }
        holder.imgBtn.setOnClickListener {
            AlertDialog.Builder(context).apply {
                setTitle("提示")
                setMessage("确定要删除该任务么？")
                setCancelable(true)
                setPositiveButton("确定"){dialog,which->
                    Repository.deleteTask1(task.id)
                    //TODO 刷新
                    val intent= Intent("com.example.my.refresh")
                    context.sendBroadcast(intent)
                }
                setNegativeButton("取消"){dialog,which->
                }
                show()
            }
        }
    }
}