package com.example.graduationprojectkotlin.ui.home.task

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.graduationprojectkotlin.R
import kotlinx.android.synthetic.main.task_fragment.*

class TaskFragment : Fragment() {

    companion object {
        fun newInstance() = TaskFragment()
    }

    private val taskList1 = ArrayList<Task>()
    private val taskList2 = ArrayList<Task>()
    private val taskList3 = ArrayList<Task>()

    private lateinit var viewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.task_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        initTasks()
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        val adapter = TaskAdapter(taskList1)
        recyclerView.adapter=adapter
    }

    private fun initTasks() {
        repeat(3) {
            taskList1.add(Task("Apple",R.drawable.ic_home_black_24dp))
            taskList1.add(Task("Banana",R.drawable.ic_home_black_24dp))
            taskList1.add(Task("Orange",R.drawable.ic_home_black_24dp))
            taskList1.add(Task("Pear",R.drawable.ic_home_black_24dp))
            taskList1.add(Task("Mango",R.drawable.ic_home_black_24dp))
        }
        repeat(3) {
            taskList2.add(Task("Apple",R.drawable.ic_home_black_24dp))
            taskList2.add(Task("Banana",R.drawable.ic_home_black_24dp))
            taskList2.add(Task("Orange",R.drawable.ic_home_black_24dp))
            taskList2.add(Task("Pear",R.drawable.ic_home_black_24dp))
            taskList2.add(Task("Mango",R.drawable.ic_home_black_24dp))
        }
        repeat(3) {
            taskList3.add(Task("Apple",R.drawable.ic_home_black_24dp))
            taskList3.add(Task("Banana",R.drawable.ic_home_black_24dp))
            taskList3.add(Task("Orange",R.drawable.ic_home_black_24dp))
            taskList3.add(Task("Pear",R.drawable.ic_home_black_24dp))
            taskList3.add(Task("Mango",R.drawable.ic_home_black_24dp))
        }
    }

}
