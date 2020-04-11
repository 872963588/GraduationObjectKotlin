package com.example.graduationprojectkotlin.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.example.graduationprojectkotlin.R
import com.example.graduationprojectkotlin.ui.home.task.TaskFragment
import com.example.graduationprojectkotlin.ui.message.MessageFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
        viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 3
            }

            override fun createFragment(position: Int)= when (position) {
                    0 -> TaskFragment()
                    1 -> MessageFragment()
                    else -> HomeFragment()
            }
        }

        TabLayoutMediator(tabLayout,viewPager2){tab, position ->
            when (position) {
                0->tab.text="Setting"
                1->tab.text="Message"
                else->tab.text="Home"
            }
        }.attach()
    }

}
