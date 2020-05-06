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
//                    return 3
                return viewModel.courseList.size
            }

            override fun createFragment(position: Int) = getTaskFragment(
                viewModel.courseList[position].id,
                viewModel.courseList[position].courseDetail,
                viewModel.courseList[position].courseImg
            )
        }
        viewPager2.adapter = adapter


        //TODO tab.text设置为课程名
        //TODO 此处没有观察者 可以和数据一起更新么？？？
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = viewModel.courseList[position].courseName
        }.attach()

        //viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
        viewModel.searchCourses("")
        viewModel.courseLiveData.observe(viewLifecycleOwner, Observer { result ->
            val courses = result.getOrNull()
            if (courses != null) {
                viewModel.courseList.clear()
                viewModel.courseList.addAll(courses)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "未能查询到任何课程", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
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
}
