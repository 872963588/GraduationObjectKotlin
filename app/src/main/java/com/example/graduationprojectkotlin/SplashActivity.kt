package com.example.graduationprojectkotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*


/*
引导页面 已登录会跳转到首页，未登录会跳转到登陆页面
 */

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val time = Timer()
        val task: TimerTask = object : TimerTask() {
            override fun run() {
                if (Repository.isUserSaved()) {
                    MainActivity.actionStart(GraduationProjectKotlinApplication.context)
                    finish()
                } else {
                    LoginActivity.actionStart(GraduationProjectKotlinApplication.context)
                    finish()
                }
            }
        }
        time.schedule(task, 2000)
        button2.setOnClickListener {
            if (Repository.isUserSaved()) {
                MainActivity.actionStart(GraduationProjectKotlinApplication.context)
            } else {
                LoginActivity.actionStart(GraduationProjectKotlinApplication.context)
            }

        }

        editText2.setOnClickListener{
            editText2.setText(textView2.text)
        }

    }


}
