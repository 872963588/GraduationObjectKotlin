package com.example.graduationprojectkotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.model.LoginInfo
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

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
        btn_login.setOnClickListener {
            val userEmail = et_user_email.text.toString()
            val userPassword = et_user_password.text.toString()
//            viewModel.userLogin(LoginInfo("4@qq.com","123465"))
//            val userInfo=Repository.userLogin(data)
//            if (userInfo!= null) {
//                Toast.makeText(this,"成功",Toast.LENGTH_SHORT).show()
//            }else{
//                Toast.makeText(this,"失败",Toast.LENGTH_SHORT).show()
//            }
            Repository.userLogin(userEmail,userPassword)
            MainActivity.actionStart(GraduationProjectKotlinApplication.context)
            finish()
        }

        //点击注册按钮跳转到登陆页面
        tv_register.setOnClickListener {
            RegisterActivity.actionStart(GraduationProjectKotlinApplication.context)

        }
//        viewModel.userLogin()
           // val result=

//            val userLoginResponse = result.getOrNull()
//            if (userLoginResponse != null) {
////                viewModel.courseList.clear()
////                viewModel.courseList.addAll(courses)
////                adapter.notifyDataSetChanged()
//                //TODO 有bug 如果第一次请求成功后再次请求 还能得到值之前的值 因为Model层的问题
//                Toast.makeText(this, "查询到用户${userLoginResponse.user.userName}", Toast.LENGTH_SHORT).show()
//
//            } else {
//                Toast.makeText(this, "未能查询用户", Toast.LENGTH_SHORT).show()
//                result.exceptionOrNull()?.printStackTrace()
//            }
//
//        }
//        viewModel.userLiveData.observe(this, Observer { result ->
//            val userLoginResponse = result.getOrNull()
//            if (userLoginResponse != null) {
////                viewModel.courseList.clear()
////                viewModel.courseList.addAll(courses)
////                adapter.notifyDataSetChanged()
//                //TODO 有bug 如果第一次请求成功后再次请求 还能得到值之前的值 因为Model层的问题
//                Toast.makeText(this, "查询到用户${userLoginResponse.user.userName}", Toast.LENGTH_SHORT).show()
//
//            } else {
//                Toast.makeText(this, "未能查询用户", Toast.LENGTH_SHORT).show()
//                result.exceptionOrNull()?.printStackTrace()
//            }
//
//        })
    }
}
