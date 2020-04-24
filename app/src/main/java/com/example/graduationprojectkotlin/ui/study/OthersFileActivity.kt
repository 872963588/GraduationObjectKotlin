package com.example.graduationprojectkotlin.ui.study

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.graduationprojectkotlin.R
import kotlinx.android.synthetic.main.activity_others_file.*

class OthersFileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_others_file)
        imageView4.setOnClickListener{
            val intent = Intent(this, Tbs1Activity::class.java).apply { putExtra("extra_data", "http://47.93.59.28:8080/AppService/123.docx") }
            startActivity(intent)
        }
    }
}
