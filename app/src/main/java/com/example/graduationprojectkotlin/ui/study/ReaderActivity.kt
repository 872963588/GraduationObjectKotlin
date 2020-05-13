package com.example.graduationprojectkotlin.ui.study

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.ContentObserver
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.graduationprojectkotlin.R
import com.tencent.smtt.sdk.TbsReaderView
import kotlinx.android.synthetic.main.activity_reader.*
import java.io.File
import java.text.DecimalFormat
import kotlin.properties.Delegates

class ReaderActivity : AppCompatActivity(), TbsReaderView.ReaderCallback {

    companion object{
        fun actionStart(context: Context) {
            val intent = Intent(context, ReaderActivity::class.java)
            //TODO 这里不对的话 就用本人的信息
            // intent.putExtra("userId", userId)
            intent.putExtra("extra_data", "http://47.93.59.28:8080/AppService/123.docx")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    lateinit var mFileUrl: String
    lateinit var mFileName: String
    lateinit var mTbsReaderView: TbsReaderView
    private lateinit var progressBar_download: ProgressBar
    private lateinit var mDownloadManager: DownloadManager
    private var mRequestId by Delegates.notNull<Long>()
    private  var mDownloadObserver: DownloadObserver? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reader)
        mFileUrl = intent.getStringExtra("extra_data")
        mTbsReaderView = TbsReaderView(this, this)
        rl_tbsView.addView(
            mTbsReaderView, RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )

        button5.setOnClickListener {
            if(comment_layout.visibility==GONE)
            comment_layout.visibility= VISIBLE else comment_layout.visibility=GONE}

        if (mFileUrl == null || mFileUrl.length <= 0) {
            Toast.makeText(
                this, "获取文件url出错了",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        mFileName = parseName(mFileUrl)
        if (isLocalExist()) {
            tv_download.setText("打开文件")
            tv_download.setVisibility(View.GONE)
            displayFile()
        } else {
            if (!mFileUrl.contains("http")) {
                AlertDialog.Builder(this)
                    .setTitle("温馨提示:")
                    .setMessage("文件的url地址不合法哟，无法进行下载")
                    .setCancelable(false)
                    .setPositiveButton(
                        "确定",
                        DialogInterface.OnClickListener { arg0, arg1 -> return@OnClickListener })
                    .create().show()
            }
            startDownload()
        }
    }

    private fun toUtf8String(url: String): String? {
        val sb = StringBuffer()
        for (i in 0 until url.length) {
            val c = url[i]
            if (c.toInt() >= 0 && c.toInt() <= 255) {
                sb.append(c)
            } else {
                var b: ByteArray
                b = try {
                    c.toString().toByteArray(charset("utf-8"))
                } catch (ex: Exception) {
                    println(ex)
                    ByteArray(0)
                }
                for (j in b.indices) {
                    var k = b[j].toInt()
                    if (k < 0) k += 256
                    sb.append("%" + Integer.toHexString(k).toUpperCase())
                }
            }
        }
        return sb.toString()
    }

    private fun displayFile() {
        val bundle = Bundle()
        bundle.putString("filePath", getLocalFile().getPath())
        bundle.putString(
            "tempPath", Environment.getExternalStorageDirectory()
                .path
        )
        val result: Boolean = mTbsReaderView.preOpen(parseFormat(mFileName), false)
        if (result) {
            mTbsReaderView.openFile(bundle)
        } else {
            val file = File(getLocalFile().getPath())
            if (file.exists()) {
                val openintent = Intent()
                openintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                val type = getMIMEType(file)
                // 设置intent的data和Type属性。
                openintent.setDataAndType( /* uri */Uri.fromFile(file), type)
                // 跳转
                startActivity(openintent)
                finish()
            }
        }
    }

    private fun getMIMEType(file: File): String {
        var type = "*/*"
        val fName = file.name
        // 获取后缀名前的分隔符"."在fName中的位置。
        val dotIndex = fName.lastIndexOf(".")
        if (dotIndex < 0) {
            return type
        }
        /* 获取文件的后缀名 */
        val end = fName.substring(dotIndex, fName.length).toLowerCase()
        if (end === "") return type
        // 在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (i in MIME_MapTable.indices) {
            if (end == MIME_MapTable.get(i).get(0)) type = MIME_MapTable.get(i).get(1)
        }
        return type
    }

    private val MIME_MapTable =
        arrayOf(
            arrayOf(".3gp", "video/3gpp"),
            arrayOf(".apk", "application/vnd.android.package-archive"),
            arrayOf(".asf", "video/x-ms-asf"),
            arrayOf(".avi", "video/x-msvideo"),
            arrayOf(".bin", "application/octet-stream"),
            arrayOf(".bmp", "image/bmp"),
            arrayOf(".c", "text/plain"),
            arrayOf(".class", "application/octet-stream"),
            arrayOf(".conf", "text/plain"),
            arrayOf(".cpp", "text/plain"),
            arrayOf(".doc", "application/msword"),
            arrayOf(
                ".docx",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
            ),
            arrayOf(".xls", "application/vnd.ms-excel"),
            arrayOf(
                ".xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            ),
            arrayOf(".exe", "application/octet-stream"),
            arrayOf(".gif", "image/gif"),
            arrayOf(".gtar", "application/x-gtar"),
            arrayOf(".gz", "application/x-gzip"),
            arrayOf(".h", "text/plain"),
            arrayOf(".htm", "text/html"),
            arrayOf(".html", "text/html"),
            arrayOf(".jar", "application/java-archive"),
            arrayOf(".java", "text/plain"),
            arrayOf(".jpeg", "image/jpeg"),
            arrayOf(".jpg", "image/jpeg"),
            arrayOf(".js", "application/x-javascript"),
            arrayOf(".log", "text/plain"),
            arrayOf(".m3u", "audio/x-mpegurl"),
            arrayOf(".m4a", "audio/mp4a-latm"),
            arrayOf(".m4b", "audio/mp4a-latm"),
            arrayOf(".m4p", "audio/mp4a-latm"),
            arrayOf(".m4u", "video/vnd.mpegurl"),
            arrayOf(".m4v", "video/x-m4v"),
            arrayOf(".mov", "video/quicktime"),
            arrayOf(".mp2", "audio/x-mpeg"),
            arrayOf(".mp3", "audio/x-mpeg"),
            arrayOf(".mp4", "video/mp4"),
            arrayOf(".mpc", "application/vnd.mpohun.certificate"),
            arrayOf(".mpe", "video/mpeg"),
            arrayOf(".mpeg", "video/mpeg"),
            arrayOf(".mpg", "video/mpeg"),
            arrayOf(".mpg4", "video/mp4"),
            arrayOf(".mpga", "audio/mpeg"),
            arrayOf(".msg", "application/vnd.ms-outlook"),
            arrayOf(".ogg", "audio/ogg"),
            arrayOf(".pdf", "application/pdf"),
            arrayOf(".png", "image/png"),
            arrayOf(".pps", "application/vnd.ms-powerpoint"),
            arrayOf(".ppt", "application/vnd.ms-powerpoint"),
            arrayOf(
                ".pptx",
                "application/vnd.openxmlformats-officedocument.presentationml.presentation"
            ),
            arrayOf(".prop", "text/plain"),
            arrayOf(".rc", "text/plain"),
            arrayOf(".rmvb", "audio/x-pn-realaudio"),
            arrayOf(".rtf", "application/rtf"),
            arrayOf(".sh", "text/plain"),
            arrayOf(".tar", "application/x-tar"),
            arrayOf(".tgz", "application/x-compressed"),
            arrayOf(".txt", "text/plain"),
            arrayOf(".wav", "audio/x-wav"),
            arrayOf(".wma", "audio/x-ms-wma"),
            arrayOf(".wmv", "audio/x-ms-wmv"),
            arrayOf(".wps", "application/vnd.ms-works"),
            arrayOf(".xml", "text/plain"),
            arrayOf(".z", "application/x-compress"),
            arrayOf(".zip", "application/x-zip-compressed"),
            arrayOf("", "*/*")
        )

    private fun parseFormat(fileName: String): String? {
        return fileName.substring(fileName.lastIndexOf(".") + 1)
    }

    private fun parseName(url: String): String {
        lateinit var fileName: String
        try {
            fileName = url.substring(url.lastIndexOf("/") + 1)
        } finally {
            if (TextUtils.isEmpty(fileName)) {
                fileName = System.currentTimeMillis().toString()
            }
        }
        return fileName
    }

    private fun isLocalExist(): Boolean {
        return getLocalFile().exists()
    }

    private fun getLocalFile(): File {
        return File(
            Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            mFileName
        )
    }

    @SuppressLint("NewApi")
    private fun startDownload() {
        mDownloadObserver = DownloadObserver(Handler())
        contentResolver.registerContentObserver(
            Uri.parse("content://downloads/my_downloads"), true,
            mDownloadObserver!!
        )
        mDownloadManager =
            getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        //将含有中文的url进行encode
        val fileUrl = toUtf8String(mFileUrl)
        try {
            val request = DownloadManager.Request(
                Uri.parse(fileUrl)
            )
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS, mFileName
            )
            request.allowScanningByMediaScanner()
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
            mRequestId = mDownloadManager.enqueue(request)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private fun queryDownloadStatus() {
        val query = DownloadManager.Query()
            .setFilterById(mRequestId)
        var cursor: Cursor? = null
        try {
            cursor = mDownloadManager.query(query)
            if (cursor != null && cursor.moveToFirst()) {
                // 已经下载的字节数
                val currentBytes = cursor
                    .getLong(
                        cursor
                            .getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR)
                    )
                // 总需下载的字节数
                val totalBytes = cursor
                    .getLong(
                        cursor
                            .getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES)
                    )
                // 状态所在的列索引
                val status = cursor.getInt(
                    cursor
                        .getColumnIndex(DownloadManager.COLUMN_STATUS)
                )
                tv_download.text = ("下载中...(" + formatKMGByBytes(currentBytes)
                        + "/" + formatKMGByBytes(totalBytes) + ")")
                // 将当前下载的字节数转化为进度位置
                val progress = (currentBytes * 1.0 / totalBytes * 100).toInt()
                progressBar_download.progress = progress
                Log.i(
                    "downloadUpdate: ", currentBytes.toString() + " " + totalBytes + " "
                            + status + " " + progress
                )
                if (DownloadManager.STATUS_SUCCESSFUL == status
                    && tv_download.visibility == View.VISIBLE
                ) {
                    tv_download.visibility = View.GONE
                    tv_download.performClick()
                    if (isLocalExist()) {
                        tv_download.visibility = View.GONE
                        displayFile()
                    }
                }
            }
        } finally {
            cursor?.close()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mTbsReaderView.onStop()
        if (mDownloadObserver != null) {
            contentResolver.unregisterContentObserver(mDownloadObserver!!)
        }
    }

    @SuppressLint("Override")
    override fun onPointerCaptureChanged(hasCapture: Boolean) {
    }

    inner class DownloadObserver(handler: Handler) : ContentObserver(handler) {
        override fun onChange(
            selfChange: Boolean,
            uri: Uri
        ) {
            queryDownloadStatus()
        }

    }


    /**
     * 将字节数转换为KB、MB、GB
     *
     * @param size 字节大小
     * @return
     */
    private fun formatKMGByBytes(size: Long): String {
        val bytes = StringBuffer()
        val format = DecimalFormat("###.00")
        if (size >= 1024 * 1024 * 1024) {
            val i = size / (1024.0 * 1024.0 * 1024.0)
            bytes.append(format.format(i)).append("GB")
        } else if (size >= 1024 * 1024) {
            val i = size / (1024.0 * 1024.0)
            bytes.append(format.format(i)).append("MB")
        } else if (size >= 1024) {
            val i = size / 1024.0
            bytes.append(format.format(i)).append("KB")
        } else if (size < 1024) {
            if (size <= 0) {
                bytes.append("0B")
            } else {
                bytes.append(size.toInt()).append("B")
            }
        }
        return bytes.toString()
    }

    override fun onCallBackAction(p0: Int?, p1: Any?, p2: Any?) {
        TODO("Not yet implemented")
    }

}
