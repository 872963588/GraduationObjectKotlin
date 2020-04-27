package com.example.graduationprojectkotlin.ui.courses.created

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.graduationprojectkotlin.R

class CoursesCreatedFragment : Fragment() {

    companion object {
        fun newInstance() = CoursesCreatedFragment()
    }

    private lateinit var viewModel: CoursesCreatedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.courses_created_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CoursesCreatedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
