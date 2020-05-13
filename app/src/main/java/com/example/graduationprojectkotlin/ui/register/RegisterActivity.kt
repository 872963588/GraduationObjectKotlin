package com.example.graduationprojectkotlin.ui.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.graduationprojectkotlin.GraduationProjectKotlinApplication
import com.example.graduationprojectkotlin.R
import kotlinx.android.synthetic.main.activity_register.*

/*
注册页面
 */
class RegisterActivity : AppCompatActivity() {

    companion object{
        fun actionStart(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
    val viewModel by lazy{ ViewModelProvider(this).get(RegisterViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //TODO 判断是否为空啥啥啥的 两次密码是否相等
        btn_register.setOnClickListener {
            val number=et_number.text.toString()
            val name=et_name.text.toString()
            val password=et_password.text.toString()
            val email=et_email.text.toString()
            val school=et_school.text.toString()
            val sex=et_sex.text.toString()
            viewModel.register(number, name, password, email, school, sex)
        }

        viewModel.userLiveData.observe(this, Observer { result ->
            val statusResponse = result.getOrNull()
            if (statusResponse != null) {
                if (statusResponse.status == "true") {
                    Toast.makeText(
                        GraduationProjectKotlinApplication.context,
                        "注册成功",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    //MainActivity.actionStart(GraduationProjectKotlinApplication.context)
                    //finish()
                } else {
                    // viewModel.init()
                    Toast.makeText(
                        GraduationProjectKotlinApplication.context,
                        "注册失败，请检查网络",
                        Toast.LENGTH_SHORT
                    ).show()
                    result.exceptionOrNull()?.printStackTrace()
                }
            }

        })
    }
}
