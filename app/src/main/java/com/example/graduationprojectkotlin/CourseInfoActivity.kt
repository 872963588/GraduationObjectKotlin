package com.example.graduationprojectkotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CourseInfoActivity : AppCompatActivity() {


    companion object{
        fun actionStart(context: Context, courseId: Int) {
            val intent = Intent(context, CourseInfoActivity::class.java)
            intent.putExtra("courseId", courseId)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_info)


    }


}
