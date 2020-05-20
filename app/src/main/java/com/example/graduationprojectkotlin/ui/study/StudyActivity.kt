package com.example.graduationprojectkotlin.ui.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.graduationprojectkotlin.R

class StudyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)
        val extraData = intent.getStringExtra("extra_data")
        val fragmentManager=supportFragmentManager
        val transaction =fragmentManager.beginTransaction()
        //TODO 不同的文件格式加载不同的Fragment
//        when (extraData) {
//            "text"->transaction.add(R.id.fragmentLayout,DocFragment())
//            "mp4"->transaction.add(R.id.fragmentLayout, VideoFragment())
//            else->transaction.add(R.id.fragmentLayout, VideoFragment())
//        }
        transaction.commit()
    }
}

