package com.example.graduationprojectkotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.graduationprojectkotlin.ui.comment.CommentFragment
import kotlinx.android.synthetic.main.activity_course_info.*

class CourseInfoActivity : AppCompatActivity() {


    companion object{
        fun actionStart(context: Context, courseId: Int) {
            val intent = Intent(context, CourseInfoActivity::class.java)
            intent.putExtra("courseId", courseId)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
    val viewModel by lazy{ ViewModelProvider(this).get(CourseInfoViewModel::class.java)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_info)

        btn_delete.setOnClickListener {
            viewModel.delete()
        }

        btn_manage.setOnClickListener {
            ManageTaskActivity.actionStart(GraduationProjectKotlinApplication.context)
        }


        viewModel.deleteLiveData.observe(this, Observer { result ->
            val statusResponse = result.getOrNull()
            if (statusResponse != null) {
                if (statusResponse.status == "true") {
                    Toast.makeText(
                        GraduationProjectKotlinApplication.context,
                        "删除成功",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    Toast.makeText(
                        GraduationProjectKotlinApplication.context,
                        "删除失败，请检查网络",
                        Toast.LENGTH_SHORT
                    ).show()
                    result.exceptionOrNull()?.printStackTrace()
                }
            }

        })


        viewModel.courseLiveData.observe(this, Observer { result ->
            val course = result.getOrNull()
            if (course != null) {
               name.setText(course.name)
                tv_owner.setText(course.owner)
                tv_detail.setText(course.detail)
                Glide.with(this).load(course.picture).into(iv_picture)
            } else {
                Toast.makeText(this, "获取失败，请查看网络", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }

        })

        viewModel.getCourseInfo(intent.getIntExtra("courseId",0).toString())


        //加载评论
        val bundle = Bundle()
        bundle.putInt("courseId", 1)


        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.comment_frag,getFragment(1))
        transaction.commit()

    }

    fun getFragment(id: Int): Fragment {
        val commentFragment = CommentFragment()
        //TODO 因为Fragment的问题，传递参数使用BUNDLE
        val bundle = Bundle()
        bundle.putInt("id", id)
        commentFragment.arguments = bundle
        return commentFragment
    }


}
