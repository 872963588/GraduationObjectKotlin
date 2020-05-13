package com.example.graduationprojectkotlin.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.graduationprojectkotlin.*
import com.example.graduationprojectkotlin.logic.util.StringUtil
import com.example.graduationprojectkotlin.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

/*
登录页面 登陆成功后finish
 */
class LoginActivity : AppCompatActivity() {

    companion object{
        fun actionStart(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }


    val viewModel by lazy{ViewModelProvider(this).get(LoginViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //点击登录按钮 进行验证 验证通过后进行登录
        btn_login.setOnClickListener {



            val email = et_user_email.text.toString()
            val password = et_user_password.text.toString()
            if (StringUtil.isEmpty(email)) {
                Toast.makeText(GraduationProjectKotlinApplication.context,"账号不能为空",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (StringUtil.isEmpty(password)) {
                Toast.makeText(GraduationProjectKotlinApplication.context,"密码不能为空",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.userLogin(email,password)
        }

        viewModel.userLiveData.observe(this, Observer { result ->
            val courses = result.getOrNull()
            if (courses != null) {
                Toast.makeText(GraduationProjectKotlinApplication.context,"登陆成功",Toast.LENGTH_SHORT).show()
                MainActivity.actionStart(
                    GraduationProjectKotlinApplication.context
                )
                finish()
            } else {
                Toast.makeText(GraduationProjectKotlinApplication.context,"用户名或密码不正确",Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        //点击注册按钮跳转到注册页面
        tv_register.setOnClickListener {
            RegisterActivity.actionStart(
                GraduationProjectKotlinApplication.context
            )

        }
    }
}
