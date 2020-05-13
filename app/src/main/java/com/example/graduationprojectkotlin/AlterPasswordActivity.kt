package com.example.graduationprojectkotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.graduationprojectkotlin.logic.util.StringUtil
import com.example.graduationprojectkotlin.logic.util.ToastUtil
import kotlinx.android.synthetic.main.activity_alter_password.*

class AlterPasswordActivity : AppCompatActivity() {

    companion object{
        fun actionStart(context: Context) {
            val intent = Intent(context, AlterPasswordActivity::class.java)
            //TODO 这里不对的话 就用本人的信息
           // intent.putExtra("userId", userId)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    val viewModel by lazy{ ViewModelProvider(this).get(AlterPasswordViewModel::class.java)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alter_password)
        btn_alter.setOnClickListener {
            val password = et_password.text.toString()
            if (StringUtil.isEmpty(password)) {
                ToastUtil.show("密码不能为空")
                return@setOnClickListener
            }
            viewModel.getPassword(password)
        }

        viewModel.statusLiveData.observe(this, Observer { result ->
            val statusResponse = result.getOrNull()
            if (statusResponse != null) {
                if (statusResponse.status == "true") {
                    Toast.makeText(
                        GraduationProjectKotlinApplication.context,
                        "修改成功",
                        Toast.LENGTH_SHORT
                    )
                        .show()
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
