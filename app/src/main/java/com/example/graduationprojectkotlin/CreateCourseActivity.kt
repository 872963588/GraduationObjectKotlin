package com.example.graduationprojectkotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CreateCourseActivity : AppCompatActivity() {

    companion object{
        fun actionStart(context: Context) {
            val intent = Intent(context, CreateCourseActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_course)
    }
}
