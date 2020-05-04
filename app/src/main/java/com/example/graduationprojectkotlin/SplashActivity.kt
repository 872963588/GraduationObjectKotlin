package com.example.graduationprojectkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.dao.UserDao
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        button2.setOnClickListener {
            if (Repository.isUserSaved()) {
                MainActivity.actionStart(GraduationProjectKotlinApplication.context)
            } else {
                LoginActivity.actionStart(GraduationProjectKotlinApplication.context)
            }

        }
    }
}
