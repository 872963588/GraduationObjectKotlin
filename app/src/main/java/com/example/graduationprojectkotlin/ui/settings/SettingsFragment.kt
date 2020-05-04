package com.example.graduationprojectkotlin.ui.settings

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.graduationprojectkotlin.*
import com.example.graduationprojectkotlin.logic.Repository

import com.example.graduationprojectkotlin.logic.dao.UserDao
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

        img_user_img.setOnClickListener {
            UserInfoActivity.actionStart(GraduationProjectKotlinApplication.context,1)
        }
        btn_help.setOnClickListener {
            TBSneiheActivity.actionStart(GraduationProjectKotlinApplication.context)
        }
        btn_exit.setOnClickListener {
            Repository.deleteUser()
            Toast.makeText(GraduationProjectKotlinApplication.context,"删除成功",Toast.LENGTH_SHORT).show()
        }
        tv_user_name.text = Repository.getSavedUser().userName

        val url = Repository.getSavedUser().userImg
        Glide.with(this).load(url).into(img_user_img)
    }

}
