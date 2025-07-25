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
import com.example.data.Bean.FavoriteVideo
import com.example.module_my.R

class FavoriteAdapter:ListAdapter<FavoriteVideo,FavoriteAdapter.FavoriteViewHolder>(FavoriteVideoDiffCallback) {

    var onItemClick :((FavoriteVideo) -> Unit)? = null

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivItem = itemView.findViewById<ImageView>(R.id.iv_item)
        val tvlike = itemView.findViewById<TextView>(R.id.tv_like_count)
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition)!!)
            }
        }
    }
    object FavoriteVideoDiffCallback : DiffUtil.ItemCallback<FavoriteVideo>() {
        override fun areItemsTheSame(oldItem: FavoriteVideo, newItem: FavoriteVideo): Boolean {
            return oldItem.videoId == newItem.videoId
        }
        override fun areContentsTheSame(oldItem: FavoriteVideo, newItem: FavoriteVideo): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.pictureitem,parent,false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
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

