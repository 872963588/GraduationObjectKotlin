package com.example.graduationprojectkotlin.ui.comment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.graduationprojectkotlin.GraduationProjectKotlinApplication
import com.example.graduationprojectkotlin.R
import com.example.graduationprojectkotlin.UserInfoActivity
import com.example.graduationprojectkotlin.logic.model.Comment

class CommentAdapter(val fragment: Fragment, private val commentList: List<Comment>): RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view){

        val userName:TextView=view.findViewById(R.id.tv_user_name)
        val commentTime:TextView=view.findViewById(R.id.tv_comment_time)
        val commentDetail:TextView=view.findViewById(R.id.tv_comment_detail)
        val userImg: ImageView =view.findViewById(R.id.user_img)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        val viewHolder = ViewHolder(view)
        //TODO 点击评论头像跳转用户信息


        return viewHolder
    }

    override fun getItemCount()=commentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment=commentList[position]
        holder.userName.text=comment.name
        holder.commentTime.text=comment.time
        holder.commentDetail.text=comment.detail
        val url = comment.picture
        Glide.with(fragment).load(url).placeholder(R.drawable.img_load).skipMemoryCache(true)//跳过内存缓存
            .diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.userImg)

        holder.itemView.setOnClickListener {
            UserInfoActivity.actionStart(GraduationProjectKotlinApplication.context,comment.userId)
        }
    }
}