package com.example.module_found.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.example.module_found.R
import com.example.module_found.bean.SpecialDetailBean
import com.example.module_found.databinding.ItemSpecialDetailBinding
import com.example.lib.time

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class RvSpDetailAdpter(private val special: SpecialDetailBean):RecyclerView.Adapter<RvSpDetailAdpter.rvSpDetailHolder>() {
    inner class rvSpDetailHolder(binding: ItemSpecialDetailBinding):RecyclerView.ViewHolder(binding.root){
        val view = binding.tvDesc
        val view2 =binding.ivCover
        val view3=binding.textView
        val view4=binding.tvTime
        init {
            itemView.setOnClickListener {
                val position=bindingAdapterPosition
                if (position!=RecyclerView.NO_POSITION){
                    val data = special.itemList?.get(position)
                    ARouter.getInstance()
                        .build("/module_video/VideoActivity")
                        .withString("playuri", data?.data?.content?.data?.playUrl)
                        .withString("cover", data?.data?.content?.data?.cover?.feed)
                        .withInt("uid", data?.data?.content?.data?.id!!)
                        .withString("title", data.data.content.data.title)
                        .withString("author",data.data.content.data.author?.name)
                        .withString("authoricon", data.data.content.data.author?.icon)
                        .withString("tags", data.data.content.data.tags?.get(0)?.name)
                        .withString("des",data.data.content.data.description)
                        .withInt("likecount", data.data.content.data.consumption?.collectionCount!!)
                        .withInt("collectcount",data.data.content.data.consumption.realCollectionCount!!)
                        .withBoolean("isLike", data.data.content.data.collected!!)
                        .withBoolean("isCollect", data.data.content.data.reallyCollected!!)
                        .withString("shareUrl", data.data.content.data.webUrl?.raw)
                        .navigation()

                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rvSpDetailHolder {
        val binding = ItemSpecialDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return rvSpDetailHolder(binding)
    }

    override fun getItemCount(): Int {
        return special.itemList?.size ?: 0
    }

    override fun onBindViewHolder(holder: rvSpDetailHolder, position: Int) {
        val item = special.itemList?.get(position)?: return
        holder.apply {
            view.text= item.data?.content?.data?.description ?: "暂无简介"
            view3.text= item.data?.content?.data?.title?:"暂无名称"
            view4.text= item.data?.content?.data?.duration?.time() ?:"0:00"
            val Url = item.data?.content?.data?.cover?.feed?.replace("http://","https://")
            if (Url != null) {
                Glide.with(itemView.context)
                    .load(Url)
                    .placeholder(R.drawable.loading2)
                    .into(view2)
            }
        }

    }
}