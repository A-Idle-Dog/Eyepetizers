package com.example.module_square.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module_square.bean.Item
import com.example.module_square.R
import com.example.module_square.databinding.ItemVpBinding

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class vpAdpter(private val list: List<String>): RecyclerView.Adapter<vpAdpter.vpHolder>() {
    inner class vpHolder(binding: ItemVpBinding) : RecyclerView.ViewHolder(binding.root){
        val v : TextView = binding.tvWord
        val v2: ImageView = binding.ivPhoto
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): vpHolder {
        val binding = ItemVpBinding.inflate(LayoutInflater.from(p0.context),p0,false)
        return vpHolder(binding)
    }

    override fun onBindViewHolder(holder: vpHolder, position: Int) {
        val url = list[position]
        Glide.with(holder.itemView)
            .load(url)
            .into(holder.v2)
        holder.v.text
    }



    override fun getItemCount(): Int {
        return list.size
    }

}