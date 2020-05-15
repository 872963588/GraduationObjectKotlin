package com.example.graduationprojectkotlin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.util.ToastUtil
import com.example.graduationprojectkotlin.ui.home.task.TaskAdapter
import kotlinx.android.synthetic.main.activity_manage_task.*
import kotlinx.android.synthetic.main.task_fragment.*
import kotlinx.android.synthetic.main.task_fragment.recyclerView
import java.util.*

class ManageTaskActivity : AppCompatActivity() {

    companion object{
        fun actionStart(context: Context,courseId: Int) {
            val intent = Intent(context, ManageTaskActivity::class.java)
            intent.putExtra("courseId",courseId)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

    }
    val viewModel by lazy{ ViewModelProvider(this).get(ManageTaskViewModel::class.java)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_task)

        val intentFilter = IntentFilter()
        intentFilter.addAction("com.example.my.refresh")
        receiver=RefreshReceiver()
        registerReceiver(receiver,intentFilter)

        val courseId = intent.getIntExtra("courseId",0)

        val time = Timer()
        val task: TimerTask = object : TimerTask() {
            override fun run() {
                //ToastUtil.show("123")
                //viewModel.searchTasks(courseId)
            }
        }
        time.schedule(task, 2000)



        btn_add.setOnClickListener {
            //Repository.deleteTask(3)
            CreateTaskActivity.actionStart(GraduationProjectKotlinApplication.context)
        }

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = TaskAdapter(this,viewModel.taskList,true)
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

    lateinit var receiver : RefreshReceiver


    inner class RefreshReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            //ToastUtil.show("123456")
            viewModel.searchTasks(this@ManageTaskActivity.intent.getIntExtra("courseId",0))
        }

    }
//    override fun onResume() {
//        super.onResume()
//
//    }

//    override fun onPause() {
//        super.onPause()
//        unregisterReceiver(receiver)
//    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }


}
