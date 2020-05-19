package com.example.graduationprojectkotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.graduationprojectkotlin.ui.study.ReaderActivity
import kotlinx.android.synthetic.main.activity_task_info.*

class TaskInfoActivity : AppCompatActivity() {

    companion object{
        fun actionStart(context: Context) {
            val intent = Intent(context, TaskInfoActivity::class.java)
            //intent.putExtra("courseId", courseId)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_info)
        button4.setOnClickListener {
            ReaderActivity.actionStart(GraduationProjectKotlinApplication.context)

        }
    }
}
