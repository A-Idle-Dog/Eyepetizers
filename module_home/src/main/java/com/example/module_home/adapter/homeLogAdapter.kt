package com.example.module_home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module_home.R
import com.example.module_home.model.Daily

class homelogadapter() : PagingDataAdapter<Daily, RecyclerView.ViewHolder>(NewsDiffCallback){

    var onItemClick: ((Daily) -> Unit)? = null

    object NewsDiffCallback : DiffUtil.ItemCallback<Daily>() {
        override fun areItemsTheSame(oldItem: Daily, newItem: Daily): Boolean {
            return true
        }
        override fun areContentsTheSame(oldItem: Daily, newItem: Daily): Boolean {
            return oldItem == newItem
        }
    }

    inner class TextCardViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView =view.findViewById<TextView>(R.id.textView)
    }

    inner class FollowCardViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val videocoverImageView =view.findViewById<ImageView>(R.id.videocover)
        val authorcoverImageView =view.findViewById<ImageView>(R.id.authorcover)
        val videotitleTextView =view.findViewById<TextView>(R.id.video_title)
        val videoresoureceTextView =view.findViewById<TextView>(R.id.video_resourece)
        val videoduration = view.findViewById<TextView>(R.id.video_duration)
        val videotags = view.findViewById<TextView>(R.id.video_tag)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition)!!)
            }
        }
    }

    companion object {
        private const val TYPE_TEXT = 0
        private const val TYPE_FOLLOW = 1
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)!!
        if(item.type == "textCard") {
            return TYPE_TEXT
        } else {
            if(item.type == "followCard") {
                return TYPE_FOLLOW
            } else {
                throw IllegalArgumentException("未知的类型")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is TextCardViewHolder -> {
                val item = getItem(position)
                if (item != null) {
                    holder.textView.text = "#"+ item.data.text
                }
            }
            is FollowCardViewHolder -> {
                val item = getItem(position)
                if(item != null){
                    Log.e("Adapter", " $item.data.content.data.cover.feed ")
                    Glide.with(holder.authorcoverImageView.context)
                        .load(item.data.header.icon)
                        .circleCrop()
                        .into(holder.authorcoverImageView)
                    Glide.with(holder.videocoverImageView.context)
                        .load(item.data.content.data.cover.feed)
                        .error(R.drawable.ic_launcher_foreground)
                        .into(holder.videocoverImageView)
                    holder.videotitleTextView.text = item.data.content.data.title
                    val duration = item.data.content.data.duration
                    if(duration!=null){
                        holder.videoduration.text = duration.secondsToTimeString()
                    }
                    holder.videoresoureceTextView.text = item.data.content.data.author.name

                    val tags = item.data.content.data.tags
                    if (tags.isNotEmpty()) {
                        holder.videotags.text = "#"+tags[0].name
                    } else {
                        holder.videotags.text = "#该视频没有标签"
                    }
                }else{
                    Log.e("Adapter", "Item at $position is null")
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_TEXT -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.logtextitem, parent, false)
                TextCardViewHolder(view)
            }
            TYPE_FOLLOW -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.logfollowitem, parent, false)
                FollowCardViewHolder(view)
            }

            else -> {
                throw IllegalArgumentException("未知的类型")
            }
        }
    }

    fun Int.secondsToTimeString(): String {
        val minutes = this / 60
        val seconds = this % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

}