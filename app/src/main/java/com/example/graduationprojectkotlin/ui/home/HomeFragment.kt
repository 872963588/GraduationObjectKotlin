package com.example.graduationprojectkotlin.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.example.graduationprojectkotlin.R
import com.example.graduationprojectkotlin.logic.Repository
import com.example.graduationprojectkotlin.ui.home.task.TaskFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    val viewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }

    private lateinit var adapter: FragmentStateAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //TODO 得到课程名，传给TaskFragment
        val bundle = Bundle()
        bundle.putInt("course", 0)

        adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return viewModel.courseList.size
            }

            override fun createFragment(position: Int) = getTaskFragment(
                viewModel.courseList[position].id,
                viewModel.courseList[position].detail,
                viewModel.courseList[position].picture
            )
        }
        viewPager2.adapter = adapter


        //TODO tab.text设置为课程名
        //TODO 此处没有观察者 可以和数据一起更新么？？？
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = viewModel.courseList[position].name
        }.attach()

        //viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel   传给userId 从数据库拿
        viewModel.searchCourses(Repository.getSavedUser().id.toString())
        viewModel.courseLiveData.observe(viewLifecycleOwner, Observer { result ->
            val courses = result.getOrNull()
            if (courses != null) {
                if (courses.isNotEmpty()) {
                    relativeLayout.visibility=View.GONE
                    linearLayout.visibility=View.VISIBLE
                    viewModel.courseList.clear()
                    viewModel.courseList.addAll(courses)
                    adapter.notifyDataSetChanged()
                } else {
                    linearLayout.visibility=View.GONE
                    relativeLayout.visibility=View.VISIBLE
                    //Toast.makeText(activity, "未能查询到任何课程", Toast.LENGTH_SHORT).show()
                    result.exceptionOrNull()?.printStackTrace()
                }
            }

        })
    }

    fun getTaskFragment(id: Int, courseDetail: String, courseImg: String): Fragment {
        val taskFragment = TaskFragment()
        //TODO 因为Fragment的问题，传递参数使用BUNDLE
        val bundle = Bundle()
        bundle.putInt("courseId", id)
        bundle.putString("courseDetail", courseDetail)
        bundle.putString("courseImg",courseImg)
        taskFragment.arguments = bundle
        return taskFragment
    }

    override fun onResume() {
        super.onResume()
        viewModel.searchCourses(Repository.getSavedUser().id.toString())
    }
}
