package com.example.module_hot.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.module_hot.bean.VideoItem
import com.example.module_hot.databinding.ItemHotBinding
import com.example.lib.time
import com.example.module_hot.R
import com.example.module_hot.databinding.ItemTextBinding

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class RvAdpter: ListAdapter<VideoItem,RecyclerView.ViewHolder>(VideoDiffCallback()) {
    private companion object{
        const val item =0
        const val text =1
    }
    inner class teHolder(val binding:ItemTextBinding):RecyclerView.ViewHolder(binding.root)
    inner class rvHolder(val binding: ItemHotBinding):RecyclerView.ViewHolder(binding.root){
        val v =binding.tvName
        val v1=binding.tvTime
        val v2=binding.ivVideo
        val v3=binding.ivAuthor
        val v4=binding.tvAclassify
        val v5=binding.tvDesVideo
        val v6=binding.tvTitle
        init {
            itemView.setOnClickListener {
                val position=bindingAdapterPosition
                if (position!=RecyclerView.NO_POSITION&&getItemViewType(position)!= text){
                    val data =getItem(position)
                    ARouter.getInstance()
                        .build("/module_video/VideoActivity")
                        .withString("playuri", data.data?.playUrl)
                        .withString("cover", data.data?.cover?.feed)
                        .withInt("uid", data.data?.id!!)
                        .withString("title",data.data.title)
                        .withString("author", data.data.author?.name)
                        .withString("authoricon", data.data.author?.icon)
                        .withString("tags", data.data.tags?.get(0)?.name)
                        .withString("des",data.data.description)
                        .withInt("likecount", data.data.consumption?.collectionCount!!)
                        .withInt("collectcount", data.data.consumption.realCollectionCount!!)
                        .withBoolean("isLike", data.data.collected!!)
                        .withBoolean("isCollect", data.data.reallyCollected!!)
                        .withString("shareUrl", data.data.webUrl?.raw)
                        .withInt("source",0)
                        .withInt("currentPosition",0)
                        .navigation()
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position==itemCount-1) text else item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType== text){
            val binding=ItemTextBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            teHolder(binding)
        }else{
            val binding = ItemHotBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return rvHolder(binding)

        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is rvHolder){
            val item=getItem(position)
            holder.apply {
                v.text= item.data?.author?.name ?: "暂无信息"
                v1.text= item.data?.duration?.time() ?: "0:00"
                v4.text="#"+ (item.data?.category ?: "")
                v5.text= item.data?.description ?: "暂无简介"
                v6.text= item.data?.title ?: "暂无标题"
                val url = item.data?.cover?.feed?.replace("http://","https://")
                val url2= item.data?.author?.icon?.replace("http://","https://")
                Glide.with(itemView)
                    .load(url)
                    .apply(RequestOptions.circleCropTransform())
                    .placeholder(R.drawable.loading2)
                    .into(v2)
                Glide.with(itemView)
                    .load(url2)
                    .placeholder(R.drawable.loading2)
                    .into(v3)
            }

        }

    }


    override fun getItemCount(): Int {
        return currentList.size+1
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
