package com.example.graduationprojectkotlin.ui.study

import android.graphics.PixelFormat
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.graduationprojectkotlin.R
import com.tencent.smtt.sdk.TbsVideo
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebSettings
import kotlinx.android.synthetic.main.activity_t_b_s.*


class TBSActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_t_b_s)
//        webView.loadUrl("http://debugtbs.qq.com")
//        webView.visibility=GONE

        //使用TBS自带的组件播放视频
//        if (TbsVideo.canUseTbsPlayer(applicationContext)) {
//            TbsVideo.openVideo(applicationContext, "http://47.93.59.28:8080/AppService/101.mp4")
//        }else{
//            Toast.makeText(this,"失败",Toast.LENGTH_SHORT).show()
//        }

//        视频为了避免闪屏和透明问题
        window.setFormat(PixelFormat.TRANSLUCENT)

        val data = Bundle()

        data.putBoolean("standardFullScreen", false)
//      true表示标准全屏，false表示X5全屏；不设置默认false

        data.putBoolean("supportLiteWnd", false)
//      false：关闭小窗；true：开启小窗；不设置默认true，

        data.putInt("DefaultVideoScreen", 1)
//      1：以页面内开始播放，2：以全屏开始播放；不设置默认：1

        webView.x5WebViewExtension.invokeMiscMethod("setVideoParams", data)
        //自动播放
        webView.settings.mediaPlaybackRequiresUserGesture=false
        webView.loadUrl("http://111.231.191.26/See%20You%20Again.mp4")

    }
}


