package com.example.graduationprojectkotlin.ui.settings

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.graduationprojectkotlin.*
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.logic.util.ToastUtil
import com.example.graduationprojectkotlin.ui.login.LoginActivity

import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        // TODO: Use the ViewModel

        button8.setOnClickListener {
            ToastUtil.show("${Repository.getSavedUser().picture}")
        }
        img_user_img.setOnClickListener {
            val intent = Intent(activity,UserInfoActivity::class.java)
            startActivityForResult(intent,2)
        }
        btn_help.setOnClickListener {
            TBSneiheActivity.actionStart(GraduationProjectKotlinApplication.context)
        }
        btn_exit.setOnClickListener {
            //TODO 弹出再次确认提示
            activity?.let { it1 ->
                AlertDialog.Builder(it1).apply {
                    setTitle("提示")
                    setMessage("确定要退出登录么？")
                    setCancelable(true)
                    setPositiveButton("确定"){dialog,which->
                        Repository.deleteUser()//TODO这里应该是退出的单词吧
                        LoginActivity.actionStart(GraduationProjectKotlinApplication.context)
                        it1.finish()
                    }
                    setNegativeButton("取消"){dialog,which->
                    }
                    show()
                }
            }


            //Toast.makeText(GraduationProjectKotlinApplication.context,"删除成功",Toast.LENGTH_SHORT).show()

        }
        btn_alter_info.setOnClickListener {
            //TODO 这里的参数改一下 不用改  没有用到 userId
            //UserInfoActivity.actionStart(GraduationProjectKotlinApplication.context,1)
            val intent = Intent(activity,UserInfoActivity::class.java)
            startActivityForResult(intent,1)
        }
        btn_alter_password.setOnClickListener {
            AlterPasswordActivity.actionStart(GraduationProjectKotlinApplication.context)
        }

        getInfo()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1->getInfo()
            2->getInfo()
        }
    }
    private fun getInfo() {
        tv_user_name.text = Repository.getSavedUser().name
        val url = Repository.getSavedUser().picture
        Glide.with(this).load(url).placeholder(R.drawable.img_load).skipMemoryCache(true)//跳过内存缓存
            .diskCacheStrategy(DiskCacheStrategy.NONE).into(img_user_img)
    }

}
