package com.example.graduationprojectkotlin

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.tencent.smtt.sdk.QbSdk
import com.tencent.smtt.sdk.QbSdk.PreInitCallback

/**
 * 全局获取Context
 */

class GraduationProjectKotlinApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        QbSdk.setDownloadWithoutWifi(true)

        //TODO 可以不处理，但是需实现
        QbSdk.initX5Environment(this, null)
    }
}