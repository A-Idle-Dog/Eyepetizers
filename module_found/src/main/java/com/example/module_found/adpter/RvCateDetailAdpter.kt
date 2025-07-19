package com.example.module_found.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lib.time
import com.example.module_found.R
import com.example.module_found.bean.Item
import com.example.module_found.databinding.ItemCateDetailBinding

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class RvCateDetailAdpter:PagingDataAdapter<Item,RvCateDetailAdpter.rvCateDetailHolder>(object :DiffUtil.ItemCallback<Item>(){
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem==newItem
    }

}) {

    inner class rvCateDetailHolder(private val binding:ItemCateDetailBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: Item?){
            binding?.apply {
                tvName.text=item?.data?.header?.title?: "未知作者"
                tvTime.text=item?.data?.content?.data?.duration?.time()?: "0:00"
                tvDesVideo.text=item?.data?.content?.data?.description?: "暂无描述"
                tvAclassify.text=item?.data?.content?.data?.category?: "未分类"
                val imUrl1 = item?.data?.header?.icon?.replace("http://","https://")
                val imUrl2 = item?.data?.content?.data?.cover?.feed?.replace("http://","https://")
                Glide.with(itemView)
                    .load(imUrl1)
                    .placeholder(R.drawable.loading2)
                    .into(ivAuthor)
                Glide.with(itemView)
                    .load(imUrl2)
                    .placeholder(R.drawable.loading2)
                    .into(ivVideo)
            }
        }
    }

    override fun onBindViewHolder(holder: rvCateDetailHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rvCateDetailHolder {
        val binding = ItemCateDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return rvCateDetailHolder(binding)
    }
}