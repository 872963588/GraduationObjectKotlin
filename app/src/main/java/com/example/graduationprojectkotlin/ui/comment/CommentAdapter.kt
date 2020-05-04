package com.example.graduationprojectkotlin.ui.comment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectkotlin.R
import com.example.graduationprojectkotlin.logic.model.Comment

class CommentAdapter(val fragment: Fragment,val commentList: List<Comment>): RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view){

        val userName:TextView=view.findViewById(R.id.tv_user_name)
        val commentTime:TextView=view.findViewById(R.id.tv_comment_time)
        val commentDetail:TextView=view.findViewById(R.id.tv_comment_detail)
        //val userImg:ImageView=view.findViewById(R.id.)

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
        holder.userName.text=comment.userId.toString()
        holder.commentTime.text=comment.commentTime
        holder.commentDetail.text=comment.commentDetail
    }
}