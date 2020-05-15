package com.example.graduationprojectkotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.graduationprojectkotlin.logic.Repository
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

        val courseId = intent.getIntExtra("courseId",0)
        viewModel.getCourseInfo(courseId.toString())


        btn_delete.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("提示")
                setMessage("确定要删除该课程么？")
                setCancelable(true)
                setPositiveButton("确定"){dialog,which->
                    viewModel.delete()
                    //TODO 删除以后跳转
                    this@CourseInfoActivity.finish()
                }
                setNegativeButton("取消"){dialog,which->
                }
                show()
            }
        }

        btn_manage.setOnClickListener {
            ManageTaskActivity.actionStart(GraduationProjectKotlinApplication.context,courseId)
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
                if (course.owner==Repository.getSavedUser().id.toString()){
                    btn_alter.visibility = View.VISIBLE
                    btn_delete.visibility = View.VISIBLE
                    btn_manage.visibility = View.VISIBLE
                    btn_join.visibility = View.GONE
                }
                tv_course_name.setText(course.name)
                //tv_owner.setText(course.owner)
                viewModel.getUserInfo(course.owner.toInt())

                //tv_owner.setText(Repository.getName(course.owner.toInt()))
                tv_detail.setText(course.detail)
                Glide.with(this).load(course.picture).into(iv_picture)
            } else {
                Toast.makeText(this, "获取失败，请查看网络", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }

        })
        viewModel.ownerLiveData.observe(this, Observer { result ->
            val user = result.getOrNull()
            if (user != null) {
                tv_owner.text = user.name
            } else {
                Toast.makeText(this, "获取失败，请查看网络", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }

        })



//        //TODO 加载评论
//        val bundle = Bundle()
//        bundle.putInt("courseId", courseId)


        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.comment_frag,getFragment(courseId))
        transaction.commit()

    }

    fun getFragment(id: Int): Fragment {
        val commentFragment = CommentFragment()
        //TODO 因为Fragment的问题，传递参数使用BUNDLE
        val bundle = Bundle()
        bundle.putInt("id", id)
        bundle.putString("type", "course")
        commentFragment.arguments = bundle
        return commentFragment
    }


}
