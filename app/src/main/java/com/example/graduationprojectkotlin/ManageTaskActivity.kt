package com.example.graduationprojectkotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationprojectkotlin.ui.home.task.TaskAdapter
import kotlinx.android.synthetic.main.activity_manage_task.*
import kotlinx.android.synthetic.main.task_fragment.*
import kotlinx.android.synthetic.main.task_fragment.recyclerView

class ManageTaskActivity : AppCompatActivity() {

    companion object{
        fun actionStart(context: Context) {
            val intent = Intent(context, ManageTaskActivity::class.java)
            intent.putExtra("courseId",1)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
    val viewModel by lazy{ ViewModelProvider(this).get(ManageTaskViewModel::class.java)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_task)

        btn_add.setOnClickListener {
            CreateTaskActivity.actionStart(GraduationProjectKotlinApplication.context)
        }

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = TaskAdapter(viewModel.taskList)
        recyclerView.adapter=adapter

        viewModel.searchTasks(intent.getIntExtra("courseId",0))

        //arguments?.getInt("courseId")?.let { viewModel.searchTasks(it) }
        viewModel.taskLiveData.observe(this, Observer { result ->
            val tasks = result.getOrNull()
            if (tasks != null) {
                viewModel.taskList.clear()
                viewModel.taskList.addAll(tasks)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "未能查询到任何任务", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }

        })
    }
}
