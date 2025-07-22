package com.example.module_found.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module_found.R
import com.example.module_found.bean.SpecialDetailBean
import com.example.module_found.databinding.ItemSpecialDetailBinding

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
        holder?.apply {
            view.text= item?.data?.content?.data?.description ?: "暂无简介"
            view3.text=item?.data?.content?.data?.title?:"暂无名称"
            val Url = item?.data?.content?.data?.cover?.feed?.replace("http://","https://")
            if (Url != null) {
                Glide.with(itemView.context)
                    .load(Url)
                    .placeholder(R.drawable.loading2)
                    .into(view2)
            }
        }

    }
}