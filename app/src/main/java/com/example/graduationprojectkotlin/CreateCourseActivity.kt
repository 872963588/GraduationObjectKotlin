package com.example.graduationprojectkotlin

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.BaseColumns
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.graduationprojectkotlin.GraduationProjectKotlinApplication.Companion.context
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.util.PathUtil
import kotlinx.android.synthetic.main.activity_create_course.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class CreateCourseActivity : AppCompatActivity() {

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, CreateCourseActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
    val viewModel by lazy{ ViewModelProvider(this).get(CreateCourseViewModel::class.java)}
    lateinit var uri :Uri
    var isUpload=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_course)

        val adapter = ArrayAdapter<String>(GraduationProjectKotlinApplication.context,android.R.layout.simple_spinner_item,data)
        spinner.adapter = adapter

        //TODO 记得传递owner
        btn_create.setOnClickListener {
            //TODO 验证
            progressBar.visibility= View.VISIBLE
            //val number=et_number.text.toString()
            val name=et_name.text.toString()
            val detail=et_detail.text.toString()
            //val email=et_email.text.toString()
            //val school=et_school.text.toString()
            //val sex=et_sex.text.toString()
            val sort=spinner.selectedItem.toString()
            viewModel.create(name,detail,sort)
        }

        viewModel.statusLiveData.observe(this, Observer { result ->
            val statusResponse = result.getOrNull()
            if (statusResponse != null) {
                if (statusResponse.status != "false") {

                    //上传图片
                    if(isUpload){
                        upload(uri,statusResponse.status)
                    }else{
                        Toast.makeText(GraduationProjectKotlinApplication.context,"创建成功", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                } else {
                    Toast.makeText(
                        GraduationProjectKotlinApplication.context,
                        "创建失败，请检查网络",
                        Toast.LENGTH_SHORT
                    ).show()
                    result.exceptionOrNull()?.printStackTrace()
                }
            }else{
                Toast.makeText(
                    GraduationProjectKotlinApplication.context,
                    "创建失败，请检查网络",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })

        viewModel.isUploaded.observe(this, Observer { result ->
            val statusResponse = result.getOrNull()
            if (statusResponse != null) {
                if (statusResponse.status == "true") {
                    Toast.makeText(GraduationProjectKotlinApplication.context,"创建成功", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(
                        GraduationProjectKotlinApplication.context,
                        "创建失败，请检查网络",
                        Toast.LENGTH_SHORT
                    ).show()
                    result.exceptionOrNull()?.printStackTrace()
                }
            }else{
                Toast.makeText(
                    GraduationProjectKotlinApplication.context,
                    "服务器异常",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })


        //TODO 这里的图片 记得学啊   记得传递owner
        button3.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
//                    data.data?.let { uri ->
//
//                    }

                    uri = data.data!!
                    isUpload=true

                    val bitmap = getBitmapFromUri(uri)
                    imageView.setImageBitmap(bitmap)
                }
            }
        }
    }

    fun upload(uri :Uri,name:String){
        //                        Log.d("123456", uri.toString())
//                        Log.d("123456", uri.path)
//                        Log.d("123456", getRealPathFromUri(context,uri))
        val file=File(PathUtil.getRealPathFromUri(context,uri))
        val builder = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
        //.addFormDataPart("abc", "abc")//在这里添加服务器除了文件之外的其他参数
        val imageBody =
            RequestBody.create(MediaType.parse("multipart/form-data"), file)
        //builder.addFormDataPart("uploadfile", file.getName(), imageBody)

        val name1 = file.getName()
        val int=name1.lastIndexOf(".")
        val name3 =name+name1.substring(int)


        builder.addFormDataPart("uploadfile",name3, imageBody)
//        val parts =
//            builder.build().parts()
//        Repository.uploadImg(parts)
//
//        finish()
        viewModel.getParts(builder.build().parts())

        // Log.d("123456", uri.getAuthority())



    }

    private fun getBitmapFromUri(uri: Uri) = contentResolver.openFileDescriptor(uri, "r")?.use {
        BitmapFactory.decodeFileDescriptor(it.fileDescriptor)

    }

    private val data = listOf(
        "哲学",
        "经济学",
        "法学",
        "教育学",
        "文学",
        "历史学",
        "理学",
        "工学",
        "农学",
        "医学",
        "军事学",
        "管理学",
        "艺术学",
        "其他"
    )



}
