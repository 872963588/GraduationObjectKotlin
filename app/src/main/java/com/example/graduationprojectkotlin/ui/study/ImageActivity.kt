package com.example.graduationprojectkotlin.ui.study

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.graduationprojectkotlin.R
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {

    companion object{
        fun actionStart(context: Context, fileUrl:String) {
            val intent = Intent(context, ImageActivity::class.java)
            intent.putExtra("fileUrl", fileUrl)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        Glide.with(this).load(intent.getStringExtra("fileUrl")).into(imageView)
    }
}
