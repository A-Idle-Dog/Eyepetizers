package com.example.module_square.adpter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module_square.bean.Item
import com.example.module_square.databinding.ItemRvJumpBinding

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class SqReAdpter: PagingDataAdapter<Item, SqReAdpter.sqReViewHolder>(
    object : DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem==newItem
        }
    }
) {
    inner class sqReViewHolder(private val binding: ItemRvJumpBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Item?){
            binding?.let {
                it.tvTitle.text = item?.data?.title
                it.text.text=item?.data?.description
                val imgUrl = item?.data?.icon?.replace("http://","https://")
                Log.d("666", "onBindViewHolder: $imgUrl")
                Glide.with(itemView)
                    .load(imgUrl)
                    .into(it.image)
            }
        }
    }

    override fun onBindViewHolder(holder: sqReViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): sqReViewHolder {
        val binding = ItemRvJumpBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return sqReViewHolder(binding)
    }
}