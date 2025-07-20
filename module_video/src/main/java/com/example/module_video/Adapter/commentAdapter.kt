package com.example.module_video.Adapter

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module_video.R
import com.example.module_video.model.Comment
import java.util.Date
import java.util.Locale

class commentAdapter :ListAdapter<Comment, commentAdapter.commentViewHolder>(NewsDiffCallback){
    object NewsDiffCallback : DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem == newItem
        }
    }

    inner class commentViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val authorIcon : ImageView = view.findViewById(R.id.ivAvatar)
        val authorName : TextView = view.findViewById(R.id.tvUsername)
        val commentTime : TextView = view.findViewById(R.id.tvTime)
        val commentContent : TextView = view.findViewById(R.id.tvContent)
        val commentLikeCount : TextView = view.findViewById(R.id.tvLikeCount)
        val ip : TextView = view.findViewById(R.id.tvIpLocation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): commentViewHolder {
        return commentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.commentitem,parent,false))
    }

    override fun onBindViewHolder(holder: commentViewHolder, position: Int) {

        val comment = getItem(position)

        Log.d("aaaaAdapter", "Binding position $position: ${comment.data?.message}")
        holder.authorName.text = comment.data.user.nickname
        holder.commentContent.text = comment.data.message
        holder.commentLikeCount.text = comment.data.likeCount.toString()
        holder.ip.text = comment.data.user.city
        Glide.with(holder.authorIcon.context)
            .load(comment.data.user.avatar)
            .circleCrop()
            .into(holder.authorIcon)
        val time = comment.data.createTime
        holder.commentTime.text = smartFormatDateTime(time)
    }
    fun smartFormatDateTime(timestamp: Long): String {
        val nowCalendar = Calendar.getInstance()
        val targetCalendar = Calendar.getInstance().apply {
            timeInMillis = timestamp
        }
        return if (nowCalendar.get(Calendar.YEAR) == targetCalendar.get(Calendar.YEAR) &&
            nowCalendar.get(Calendar.MONTH) == targetCalendar.get(Calendar.MONTH) &&
            nowCalendar.get(Calendar.DAY_OF_MONTH) == targetCalendar.get(Calendar.DAY_OF_MONTH)) {
            SimpleDateFormat("HH:mm", Locale.CHINA).format(Date(timestamp))
        } else {
            SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(Date(timestamp))
        }
    }
}