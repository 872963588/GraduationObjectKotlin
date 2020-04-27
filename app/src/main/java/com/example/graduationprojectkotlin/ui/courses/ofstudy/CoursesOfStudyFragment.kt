package com.example.graduationprojectkotlin.ui.courses.ofstudy

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.graduationprojectkotlin.R

class CoursesOfStudyFragment : Fragment() {

    companion object {
        fun newInstance() = CoursesOfStudyFragment()
    }

    private lateinit var viewModel: CoursesOfStudyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.courses_of_study_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CoursesOfStudyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
