package com.example.graduationprojectkotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.util.StringUtil
import com.example.graduationprojectkotlin.logic.util.ToastUtil
import kotlinx.android.synthetic.main.activity_user_info.*
import kotlinx.android.synthetic.main.settings_fragment.*

class UserInfoActivity : AppCompatActivity() {

    companion object{
        fun actionStart(context: Context,userId :Int) {
            val intent = Intent(context, UserInfoActivity::class.java)
            //TODO 这里不对的话 就用本人的信息
            intent.putExtra("userId", userId)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    val viewModel by lazy{ ViewModelProvider(this).get(UserInfoViewModel::class.java)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        //TODO 获取指定的用户信息

        //获取保存的用户信息
        val user = Repository.getSavedUser()

        //填充用户信息
        val url = user.picture
        Glide.with(this).load(url).into(iv_picture)
        et_number.setText(user.number)
        et_name.setText(user.name)
        et_email.setText(user.email)
        et_school.setText(user.school)
        et_sex.setText(user.sex)

        btn_alter.setOnClickListener {
            val number=et_number.text.toString()
            val name=et_name.text.toString()
            val email=et_email.text.toString()
            val school=et_school.text.toString()
            val sex=et_sex.text.toString()

            if (StringUtil.isEmpty(number)) {
                ToastUtil.show("学号不能为空")
                return@setOnClickListener
            }
            if (StringUtil.isEmpty(name)) {
                ToastUtil.show("姓名不能为空")
                return@setOnClickListener
            }
            if (StringUtil.isEmpty(email)) {
                ToastUtil.show("邮箱不能为空")
                return@setOnClickListener
            }

            viewModel.getUserInfo(number, name, email, school, sex)
        }


        viewModel.userLiveData.observe(this, Observer { result ->
            val statusResponse = result.getOrNull()
            if (statusResponse != null) {
                if (statusResponse.status == "true") {
                    Toast.makeText(
                        GraduationProjectKotlinApplication.context,
                        "修改成功",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    //TODO 改为不可编辑？直接返回吧
                    //MainActivity.actionStart(GraduationProjectKotlinApplication.context)
                    finish()
                } else {
                    Toast.makeText(
                        GraduationProjectKotlinApplication.context,
                        "修改失败，请检查网络",
                        Toast.LENGTH_SHORT
                    ).show()
                    result.exceptionOrNull()?.printStackTrace()
                }
            }

        })
    }


}
