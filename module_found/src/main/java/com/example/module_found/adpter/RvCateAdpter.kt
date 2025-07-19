package com.example.module_found.adpter
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.module_found.FoundActivity
import com.example.module_found.bean.CategoryBean
import com.example.module_found.databinding.ItemCateBinding

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class RvCateAdpter(private val categoryList: List<CategoryBean>):RecyclerView.Adapter<RvCateAdpter.rvCateViewHolder>() {
    inner class rvCateViewHolder(val binding: ItemCateBinding) :RecyclerView.ViewHolder(binding.root){
        val tv:TextView=binding.tvClassifyItem
        init {
            initListener()
        }
        private fun initListener(){
            itemView.setOnClickListener {
                val item = categoryList[absoluteAdapterPosition]
                FoundActivity.startDetail(
                    itemView.context,
                    item.tagId.toString(),
                    item.description,
                    tv,
                    absoluteAdapterPosition,
                    item.bgPicture.toString()
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rvCateViewHolder {
        val binding = ItemCateBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return rvCateViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return categoryList.size
    }

    override fun onBindViewHolder(holder: rvCateViewHolder, position: Int) {
        val item = categoryList[position]
        holder.tv.text=item.name
    }
}