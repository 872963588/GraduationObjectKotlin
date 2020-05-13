package com.example.graduationprojectkotlin.logic.util

import android.widget.Toast
import com.example.graduationprojectkotlin.GraduationProjectKotlinApplication

object ToastUtil {
    fun show(text: String) {
        Toast.makeText(GraduationProjectKotlinApplication.context,text,Toast.LENGTH_SHORT).show()
    }
}