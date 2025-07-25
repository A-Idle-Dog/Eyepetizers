package com.example.module_found.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module_found.R
import com.example.module_found.SpecialDetialActivity
import com.example.module_found.bean.SpecialDetailBean
import com.example.module_found.databinding.ItemSpecialBinding


class RvSpAdpter(private val specialList: List<SpecialDetailBean>):RecyclerView.Adapter<RvSpAdpter.rvSpHolder>() {

    inner class rvSpHolder(val binding: ItemSpecialBinding):RecyclerView.ViewHolder(binding.root){
        val v =binding.imSpecial
        val v1=binding.tvSpecial
        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position!=RecyclerView.NO_POSITION){
                        val special = specialList[position]
                        SpecialDetialActivity.actionStart(itemView.context,
                            special.id.toString(),
                            special.brief.toString(),
                            special.headerImage?.replace("http://", "https://").toString(),
                            binding.imSpecial)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rvSpHolder {
        val binding = ItemSpecialBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return rvSpHolder(binding)
    }

    override fun getItemCount(): Int {
        return specialList.size
    }

    override fun onBindViewHolder(holder: rvSpHolder, position: Int) {
        val item = specialList[position]
        holder.v1.text=item.brief?:""
        val imUrl = item.headerImage?.replace("http://","https://")
        Glide.with(holder.itemView.context)
            .load(imUrl)
            .placeholder(R.drawable.loading2)
            .into(holder.v)
    }
}