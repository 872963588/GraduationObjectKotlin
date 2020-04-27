package com.example.graduationprojectkotlin.ui.study

import android.graphics.PixelFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.graduationprojectkotlin.R
import kotlinx.android.synthetic.main.activity_video.*

/*
使用TBS X5内核播放视频
 */
class VideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

//        视频为了避免闪屏和透明问题
        window.setFormat(PixelFormat.TRANSLUCENT)

        val data = Bundle()

        data.putBoolean("standardFullScreen", false)
//      true表示标准全屏，false表示X5全屏；不设置默认false

        data.putBoolean("supportLiteWnd", false)
//      false：关闭小窗；true：开启小窗；不设置默认true，

        data.putInt("DefaultVideoScreen", 1)
//      1：以页面内开始播放，2：以全屏开始播放；不设置默认：1

        //TODO 没有内核的话怎么播放
        webView.x5WebViewExtension.invokeMiscMethod("setVideoParams", data)
        //自动播放
        webView.settings.mediaPlaybackRequiresUserGesture=false
        webView.loadUrl("http://111.231.191.26/See%20You%20Again.mp4")

    }
}


