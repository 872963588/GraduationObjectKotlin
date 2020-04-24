package com.example.graduationprojectkotlin.ui.study

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import com.example.graduationprojectkotlin.R
import com.tencent.smtt.sdk.TbsVideo

class VideoFragment : Fragment() {

    companion object {
        fun newInstance() = VideoFragment()
    }

    private lateinit var viewModel: VideoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.video_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VideoViewModel::class.java)
        // TODO: Use the ViewModel

        if (TbsVideo.canUseTbsPlayer(context)) {
            TbsVideo.openVideo(context, "https://vdept.bdstatic.com/3348795635316e6b7a454a527a6d5749/5a67423355546845/429d64abbf4598265b746a2f01e849b280b5ab3132d77d694a1db126374d09d5311ae6907a36f70a65adafa165432723.mp4?auth_key=1587540814-0-0-86b83d49e19d7895254478929a98d91e")
        }else{
            Toast.makeText(context,"失败", Toast.LENGTH_SHORT).show()
        }
    }

}
