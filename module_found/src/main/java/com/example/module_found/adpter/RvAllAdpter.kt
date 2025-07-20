package com.example.module_found.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module_found.SpecialDetialActivity
import com.example.module_found.bean.SpecialDetailBean
import com.example.module_found.databinding.ItemRvAllBinding

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class RvAllAdpter :PagingDataAdapter<SpecialDetailBean,RvAllAdpter.rvAllHolder> (object :
    DiffUtil.ItemCallback<SpecialDetailBean>(){
    override fun areItemsTheSame(oldItem: SpecialDetailBean, newItem: SpecialDetailBean): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(
        oldItem: SpecialDetailBean,
        newItem: SpecialDetailBean
    ): Boolean {
        return oldItem==newItem
    }
}){
    inner class rvAllHolder(private val binding: ItemRvAllBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(specialDetailBean: SpecialDetailBean?){
            binding?.apply {
                tvSpename.text=specialDetailBean?.brief.toString()
                tvAll.text=specialDetailBean?.text.toString()
                val imUrl = specialDetailBean?.headerImage?.replace("http://","https://")
                Glide.with(itemView)
                    .load(imUrl)
                    .into(ivAll)
            }
        }
        init {
            binding.imAll.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val special = getItem(position)
                    if (special == null) {
                        Toast.makeText(itemView.context, "请稍后再试", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    val id = special?.id.toString()
                    val brief = special.brief.toString()
                    val imgUrl = special?.headerImage?.replace("http://", "https://") ?: ""
                    SpecialDetialActivity.actionStart(
                        itemView.context,
                        id,
                        brief,
                        imgUrl,
                        binding.ivAll)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: rvAllHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rvAllHolder {
        val binding=ItemRvAllBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return rvAllHolder(binding)
    }
}