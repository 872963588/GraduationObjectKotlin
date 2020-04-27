package com.example.graduationprojectkotlin.ui.settings

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.graduationprojectkotlin.GraduationProjectKotlinApplication

import com.example.graduationprojectkotlin.R
import com.example.graduationprojectkotlin.TBSneiheActivity
import com.example.graduationprojectkotlin.UserInfoActivity
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
    }

}
