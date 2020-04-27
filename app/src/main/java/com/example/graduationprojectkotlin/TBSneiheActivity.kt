package com.example.graduationprojectkotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_t_b_sneihe.*

class TBSneiheActivity : AppCompatActivity() {

    companion object{
        fun actionStart(context: Context) {
            val intent = Intent(context, TBSneiheActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_t_b_sneihe)
        webView.loadUrl("http://debugtbs.qq.com")
    }
}
