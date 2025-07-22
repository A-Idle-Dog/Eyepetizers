package com.example.module_hot.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module_hot.bean.VideoItem
import com.example.module_hot.databinding.ItemHotBinding
import com.example.lib.time
import com.example.module_hot.R

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class RvAdpter: ListAdapter<VideoItem,RvAdpter.rvHolder>(VideoDiffCallback()) {
    inner class rvHolder(val binding: ItemHotBinding):RecyclerView.ViewHolder(binding.root){
        val v =binding.tvName
        val v1=binding.tvTime
        val v2=binding.ivVideo
        val v3=binding.ivAuthor
        val v4=binding.tvAclassify
        val v5=binding.tvDesVideo
        val v6=binding.tvTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rvHolder {
        val binding = ItemHotBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return rvHolder(binding)
    }

    override fun onBindViewHolder(holder: rvHolder, position: Int) {
        val item=getItem(position)
        holder?.apply {
            v.text= item.data?.author?.name ?: "暂无信息"
            v1.text= item.data?.duration?.time() ?: "0:00"
            v4.text="#"+ (item.data?.category ?: "")
            v5.text= item.data?.description ?: "暂无简介"
            v6.text= item.data?.title ?: "暂无标题"
            val url = item.data?.cover?.feed?.replace("http://","https://")
            val url2= item.data?.author?.icon?.replace("http://","https://")
            Glide.with(itemView)
                .load(url)
                .placeholder(R.drawable.loading2)
                .into(v2)
            Glide.with(itemView)
                .load(url2)
                .placeholder(R.drawable.loading2)
                .into(v3)
        }
    }
}
class VideoDiffCallback : DiffUtil.ItemCallback<VideoItem>() {
    override fun areItemsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
        return oldItem == newItem
    }
}
