package com.example.graduationprojectkotlin.ui.courses

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.graduationprojectkotlin.CreateCourseActivity
import com.example.graduationprojectkotlin.GraduationProjectKotlinApplication
import com.example.graduationprojectkotlin.R
import com.example.graduationprojectkotlin.ui.courses.all.AllCoursesFragment
import com.example.graduationprojectkotlin.ui.courses.created.CoursesCreatedFragment
import com.example.graduationprojectkotlin.ui.courses.ofstudy.CoursesOfStudyFragment
import com.example.graduationprojectkotlin.ui.search.SearchActivity
import com.example.graduationprojectkotlin.ui.study.VideoActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.courses_fragment.*


class CoursesFragment : Fragment() {

    companion object {
        fun newInstance() = CoursesFragment()
    }

    val viewModel by lazy { ViewModelProvider(this).get(CoursesViewModel::class.java) }

    private lateinit var adapter: FragmentStateAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.courses_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(CoursesViewModel::class.java)
        // TODO: Use the ViewModel

        button.setOnClickListener {
//            val intent = Intent(context, SearchActivity::class.java)
//            startActivity(intent)
            SearchActivity.actionStart(GraduationProjectKotlinApplication.context)
        }

        btn_create_course.setOnClickListener {
            CreateCourseActivity.actionStart(GraduationProjectKotlinApplication.context)
        }


        adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 3
            }

            override fun createFragment(position: Int) = getFragment(position)
        }
        viewPager2.adapter=adapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "所有课程"
                1 -> tab.text = "创建的课程"
                2 -> tab.text = "学习的课程"
                // else -> tab.text = "Home"
            }
        }.attach()
    }

    fun getFragment(position: Int): Fragment {

        return when (position) {
            0 -> AllCoursesFragment()
            1 -> CoursesCreatedFragment()
            2 -> CoursesOfStudyFragment()

            else -> CoursesOfStudyFragment()
        }
    }

}
