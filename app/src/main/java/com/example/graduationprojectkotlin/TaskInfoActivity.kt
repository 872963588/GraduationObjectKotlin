package com.example.graduationprojectkotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.text.htmlEncode
import androidx.fragment.app.Fragment
import com.example.graduationprojectkotlin.ui.comment.CommentFragment
import com.example.graduationprojectkotlin.ui.study.ImageActivity
import com.example.graduationprojectkotlin.ui.study.ReaderActivity
import com.example.graduationprojectkotlin.ui.study.VideoActivity
import kotlinx.android.synthetic.main.activity_task_info.*

class TaskInfoActivity : AppCompatActivity() {

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, TaskInfoActivity::class.java)
            //intent.putExtra("courseId", courseId)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_info)

        val fileUrl=intent.getStringExtra("fileUrl")

        tv_time.text = intent.getStringExtra("time")
        tv_name.text = intent.getStringExtra("name")
        tv_detail.text = intent.getStringExtra("detail")
        tv_file_type.text = intent.getStringExtra("fileType")
        tv_file_url.text = intent.getStringExtra("fileUrl")
        if (fileUrl!=null&&fileUrl.isNotEmpty()) {
            tv_file_name.text=tv_file_url.text.substring(tv_file_url.text.toString().lastIndexOf("/")+1)
            linearLayout.visibility = View.VISIBLE
        } else {
            linearLayout.visibility = View.GONE

        }
        button4.setOnClickListener {
            //ReaderActivity.actionStart(GraduationProjectKotlinApplication.context)
            var a =""

            for(type in videoList){
                if (tv_file_type.text == type) {
                    a="video"
                    break
                }
            }
            for(type in imgList){
                if (tv_file_type.text == type) {
                    a="image"
                    break
                }
            }
            if (a=="video"){
                VideoActivity.actionStart(this,fileUrl)
            }
            else if(a=="image"){
                ImageActivity.actionStart(this,fileUrl)
            }else{
                ReaderActivity.actionStart(this,fileUrl)
            }


        }

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.comment_frag,getFragment(intent.getIntExtra("id",0)))
        transaction.commit()

    }

    fun getFragment(id: Int): Fragment {
        val commentFragment = CommentFragment()
        //TODO 因为Fragment的问题，传递参数使用BUNDLE
        val bundle = Bundle()
        bundle.putInt("id", id)
        bundle.putString("type", "task")
        commentFragment.arguments = bundle
        return commentFragment
    }

    val videoList= listOf("avi","wmv","mpeg","mp4","mov","asf","flv","m4v","rmvb","rm","3gp","vob")
    val imgList= listOf("bmp","jpg","jpeg","png","tif","gif","pcx","tga","exif","fpx","svg","psd","cdr","pcd","dxf","ufo","eps","ai","raw","WMF","webp")
}
