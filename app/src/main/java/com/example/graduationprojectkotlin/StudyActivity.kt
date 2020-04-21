package com.example.graduationprojectkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.graduationprojectkotlin.ui.study.DocFragment
import com.example.graduationprojectkotlin.ui.study.VideoFragment

class StudyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)
        val extraData = intent.getIntExtra("extra_data",0)
        val fragmentManager=supportFragmentManager
        val transaction =fragmentManager.beginTransaction()
        //TODO 不同的文件格式加载不同的Fragment
        when (extraData) {
            1->transaction.add(R.id.fragmentLayout,DocFragment())
            else->transaction.add(R.id.fragmentLayout, VideoFragment())
        }

        transaction.commit()
    }
}

