package com.example.photo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photo.R
import com.example.photo.bean.photoData

class VPadapter(list: List<String>): RecyclerView.Adapter<VPadapter.VPViewHolder>() {
    private var list = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VPViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photoitem,parent,false)
        return VPViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VPViewHolder, position: Int) {
        Glide.with(holder.imageView.context)
            .load(list[position])
            .into(holder.imageView)

    }

    inner class VPViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.ivPhoto)
    }
}