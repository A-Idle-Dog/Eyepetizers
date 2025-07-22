package com.example.module_square.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.module_square.R
import com.example.module_square.bean.Rec
import com.example.module_square.databinding.ItemBannerBinding
import com.example.module_square.databinding.ItemRvBinding

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class reAdpter : PagingDataAdapter<Rec,RecyclerView.ViewHolder>(object :DiffUtil.ItemCallback<Rec>(){
    override fun areItemsTheSame(oldItem: Rec, newItem: Rec): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Rec, newItem: Rec): Boolean {
        return oldItem.title == newItem.title
    }


}) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position) ?: return
        if (holder is rvHolder) {
            holder.bind(item)
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rvHolder {
        val binding=ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return rvHolder(binding)
    }
    inner class rvHolder(private val binding: ItemRvBinding):RecyclerView.ViewHolder(binding.root){
        private val cover = binding.ivPhoto
        private val title=binding.tvName
        private val author=binding.ivAuthor
        fun bind(data:Rec){
            Glide.with(itemView.context)
                .load(data.coverUrl).apply(RequestOptions().fitCenter())
                .into(cover)
            Glide.with(itemView.context)
                .load(data.icon).circleCrop()
                .into(author)
            title.text=data.title
        }

    }

}






