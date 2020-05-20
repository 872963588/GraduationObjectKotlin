package com.example.graduationprojectkotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.util.PathUtil
import kotlinx.android.synthetic.main.activity_create_task.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class CreateTaskActivity : AppCompatActivity() {

    companion object{
        fun actionStart(context: Context,courseId:Int) {
            val intent = Intent(context, CreateTaskActivity::class.java)
            intent.putExtra("courseId",courseId)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
    val viewModel by lazy{ ViewModelProvider(this).get(CreateTaskViewModel::class.java)}
    lateinit var uri : Uri
    var isUpload=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)


        btn_add_file.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "*/*"
            startActivityForResult(intent, 1)
        }

        btn_create.setOnClickListener {
            //val number=et_number.text.toString()
            val name=et_name.text.toString()
            val detail=et_detail.text.toString()
            //val time = editText.text.toString()
            //val email=et_email.text.toString()
            //val school=et_school.text.toString()
            //val sex=et_sex.text.toString()
            viewModel.create(name,detail,intent.getIntExtra("courseId",0))
        }

        viewModel.statusLiveData.observe(this, Observer { result ->
            val statusResponse = result.getOrNull()
            if (statusResponse != null) {
                if (statusResponse.status != "false") {
                    val intent= Intent("com.example.my.refresh")
                    sendBroadcast(intent)
                    Toast.makeText(GraduationProjectKotlinApplication.context,"创建成功", Toast.LENGTH_SHORT).show()
                    //MainActivity.actionStart(GraduationProjectKotlinApplication.context)
                    if(isUpload){
                        upload(uri,statusResponse.status)
                    }else{
                        finish()
                    }

                    //finish()
                    // onBackPressed()
                } else {
                    // viewModel.init()
                    Toast.makeText(
                        GraduationProjectKotlinApplication.context,
                        "创建失败，请检查网络",
                        Toast.LENGTH_SHORT
                    ).show()
                    result.exceptionOrNull()?.printStackTrace()
                }
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    uri = data.data!!
                    isUpload=true

                    //TODO 修改Text名字
//                    val bitmap = getBitmapFromUri(uri)
//                    imageView.setImageBitmap(bitmap)
                }
            }
        }
    }
    fun upload(uri :Uri,name:String){
        val file= File(PathUtil.getRealPathFromUri(this,uri))
        val builder = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
        //.addFormDataPart("abc", "abc")//在这里添加服务器除了文件之外的其他参数
        val imageBody =
            RequestBody.create(MediaType.parse("multipart/form-data"), file)
        //builder.addFormDataPart("uploadfile", file.getName(), imageBody)

        val name1 = file.getName()
        val index=name1.lastIndexOf(".")
        val name3 =name+name1.substring(index)


        builder.addFormDataPart("uploadfile",name3, imageBody)
        val parts =
            builder.build().parts()
        Repository.uploadFile(parts)

        finish()

        // Log.d("123456", uri.getAuthority())



    }
}
