package com.example.graduationprojectkotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.util.PathUtil
import com.example.graduationprojectkotlin.logic.util.StringUtil
import com.example.graduationprojectkotlin.logic.util.ToastUtil
import kotlinx.android.synthetic.main.activity_create_course.*
import kotlinx.android.synthetic.main.activity_user_info.*
import kotlinx.android.synthetic.main.activity_user_info.et_name
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class UserInfoActivity : AppCompatActivity() {

    companion object {
        fun actionStart(context: Context, userId: Int) {
            val intent = Intent(context, UserInfoActivity::class.java)
            //TODO 这里不对的话 就用本人的信息
            intent.putExtra("userId", userId)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    val viewModel by lazy { ViewModelProvider(this).get(UserInfoViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        //TODO 获取指定的用户信息

        val userId = intent.getIntExtra("userId", 0)

        if (userId == 0) {
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


        } else {
            viewModel.getUserInfo(userId)
        }

        iv_picture.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }


        btn_alter.setOnClickListener {
            val number = et_number.text.toString()
            val name = et_name.text.toString()
            val email = et_email.text.toString()
            val school = et_school.text.toString()
            val sex = et_sex.text.toString()

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

            viewModel.setUserInfo(number, name, email, school, sex)
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


        viewModel.userLiveData.observe(this, Observer { result ->
            val user = result.getOrNull()
            if (user != null) {
                val url = user.picture
                Glide.with(this).load(url).into(iv_picture)
                et_number.setText(user.number)
                et_name.setText(user.name)
                et_email.setText(user.email)
                et_school.setText(user.school)
                et_sex.setText(user.sex)
            } else {
                Toast.makeText(this, "获取失败，请查看网络", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }

        })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    data.data?.let { uri ->
//                        Log.d("123456", uri.toString())
//                        Log.d("123456", uri.path)
//                        Log.d("123456", getRealPathFromUri(context,uri))
                        val file= File(
                            PathUtil.getRealPathFromUri(
                                GraduationProjectKotlinApplication.context,uri))
                        val builder = MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                        //.addFormDataPart("abc", "abc")//在这里添加服务器除了文件之外的其他参数
                        val imageBody =
                            RequestBody.create(MediaType.parse("multipart/form-data"), file)
                        //builder.addFormDataPart("uploadfile", file.getName(), imageBody)
                        val name = file.getName()
                        val index=name.lastIndexOf(".")
                        val name3 = "user_${Repository.getSavedUser().id}"+name.substring(index)
                        builder.addFormDataPart("uploadfile",name3, imageBody)
                        val parts =
                            builder.build().parts()
                        Repository.uploadImg(parts)
                        viewModel.picture="http://47.93.59.28:8080/Study/images/${name3}"

                       // Log.d("123456", uri.getAuthority())

                        val bitmap = getBitmapFromUri(uri)
                        iv_picture.setImageBitmap(bitmap)

                    }
                }
            }
        }
    }

    private fun getBitmapFromUri(uri: Uri) = contentResolver.openFileDescriptor(uri, "r")?.use {
        BitmapFactory.decodeFileDescriptor(it.fileDescriptor)

    }
}
