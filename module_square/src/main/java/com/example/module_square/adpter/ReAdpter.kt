package com.example.module_square.adpter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.module_square.R
import com.example.module_square.SquareActivity
import com.example.module_square.bean.Tab
import com.example.module_square.databinding.ItemRvBinding
import com.example.module_square.bean.TabListBean
import com.example.module_square.bean.ChildTabBean
import com.example.module_square.bean.Item

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class reAdpter(private val tabListBean :TabListBean,private  val child : MutableList<ChildTabBean>) : RecyclerView.Adapter<reAdpter.reViewHolder>() {
    inner class reViewHolder(private val binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root){
        val v : TextView =itemView.findViewById(R.id.tv_name)
        val v2 : ViewPager2 =itemView.findViewById(R.id.vp_com)
        init {
            itemView.setOnClickListener {
                val childTab = tabListBean.tabInfo.tabList[bindingAdapterPosition]
                val id = childTab.id
                if (id>0){
                    SquareActivity.startActivity(id.toString(),itemView.context,childTab.name)
                }else{
                    SquareActivity.startActivity("0",itemView.context,childTab.name)
                }

            }
        }

    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): reViewHolder {
        val binding = ItemRvBinding.inflate(LayoutInflater.from(p0.context),p0,false)
        return reViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tabListBean.tabInfo.tabList.size
    }

    override fun onBindViewHolder(p0: reViewHolder, p1: Int) {

        val item : Tab = tabListBean.tabInfo.tabList[p1]
        Log.d("666", "onBindViewHolder: $item")
        p0.v.text = item.name
        val childitem = child[p1]
        Log.d("666", "onBindViewHolder: $childitem")
        val adapter : vpAdpter = vpAdpter(childitem.itemList as List<Item>)
        p0.v2.adapter = adapter
    }

}