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

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class vpAdpter(private val list: List<Item>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class vpHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val v : TextView = itemView.findViewById(R.id.tv_word)
        val v2: ImageView = itemView.findViewById(R.id.iv_photo)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): vpHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.item_vp,p0,false)
        return vpHolder(v)
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val realHolder = p0 as vpHolder
        val item = list[p1]
        realHolder.v.text=item.data.title
        val imageUrl = item.data.icon.replace("http://", "https://")
        Glide.with(p0.itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.loading2)
            .into(realHolder.v2)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}