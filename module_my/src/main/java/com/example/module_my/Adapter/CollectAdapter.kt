package com.example.module_my.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.data.Bean.CollectVideo
import com.example.data.Bean.FavoriteVideo
import com.example.module_my.R

class CollectAdapter: ListAdapter<CollectVideo, CollectAdapter.CollectViewHolder>(CollectVideoDiffCallback) {

    var onItemClick :((CollectVideo,Int) -> Unit)? = null

    inner class CollectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivItem = itemView.findViewById<ImageView>(R.id.iv_item)
        val tvlike = itemView.findViewById<TextView>(R.id.tv_like_count)
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition)!!,position)
            }
        }
    }
    object CollectVideoDiffCallback : DiffUtil.ItemCallback<CollectVideo>() {
        override fun areItemsTheSame(oldItem: CollectVideo, newItem: CollectVideo): Boolean {
            return oldItem.videoId == newItem.videoId
        }
        override fun areContentsTheSame(oldItem: CollectVideo, newItem: CollectVideo): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.pictureitem,parent,false)
        return CollectViewHolder(view)
    }

    override fun onBindViewHolder(holder: CollectViewHolder, position: Int) {
        val item = getItem(position)
        if(item != null){
            Glide.with(holder.ivItem.context)
                .load(item.cover)
                .error(R.drawable.img)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.ivItem)
            holder.tvlike.text = item.likecount.toString()
        }
    }
}