package com.example.module_square.adpter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.module_square.R
import com.example.module_square.bean.Squarepic
import com.example.module_square.databinding.ItemRvBinding

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class reAdpter : PagingDataAdapter<Squarepic,RecyclerView.ViewHolder>(object :DiffUtil.ItemCallback<Squarepic>(){
    override fun areItemsTheSame(oldItem: Squarepic, newItem: Squarepic): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Squarepic, newItem: Squarepic): Boolean {
        return oldItem.title == newItem.title
    }


}) {


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position) ?: return
        if (holder is rvHolder) {
            holder.bind(item)

        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder {

                val binding=ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return rvHolder(binding)

        }
    inner class rvHolder(binding: ItemRvBinding):RecyclerView.ViewHolder(binding.root){
        internal val cover = binding.ivPhoto
        private val title=binding.tvName
        private val author=binding.ivAuthor

        private var currentUrl : String? = null
        private var currentData: Squarepic? = null

        private var columnWidth: Int = 0

        init {

            cover.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    // 移除监听，避免重复调用
                    cover.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    // 列宽 = ImageView宽度
                    columnWidth = cover.width
                    // 若首次布局时已有数据，重新绑定一次
                    currentData?.let { bind(it) }
                }
            })



            itemView.setOnClickListener {
                val position =bindingAdapterPosition
                if (position!=RecyclerView.NO_POSITION){
                    val data=getItem(position)
                    val safeTag = if (data?.tags.isNullOrEmpty()) {
                        ""
                    } else {
                        data?.tags?.get(0)?.name ?: ""
                    }
                    val arrayList = data?.picUrls?.let { ArrayList(it) }
                    Log.d("wyfwyf", "onBindViewHolder: $arrayList")
                    Log.d("wyfwyf", "onBindViewHolder: ${data?.time}")
                    ARouter.getInstance()
                        .build("/photo/photo")
                        .withStringArrayList("picUrls", arrayList)
                        .withString("icon", data?.icon)
                        .withString("author", data?.author)
                        .withString("title",data?.title)
                        .withInt("likecount", data?.clloect!!)
                        .withInt("collectcount",data.realCollect)
                        .withString("tag",safeTag)
                        .withBoolean("isliked",data.liked)
                        .withInt("uid" ,data.uid)
                        .withString("ip",data.ip)
                        .withLong("time",data.time)
                        .withInt("currentPosition",0)
                        .navigation()
                }
            }
        }

        @SuppressLint("SuspiciousIndentation")
        fun bind(data:Squarepic){
            currentData = data

                if (columnWidth <= 0) return // 宽度未测量完成，跳过

                val targetHeight = if (data.picWidth > 0 && data.picHight > 0) {
                    (data.picHight.toFloat() / data.picWidth * columnWidth).toInt()
                } else {

                    columnWidth // 默认正方形
                }
                cover.layoutParams.height = targetHeight

            Glide.with(itemView.context)
                .asBitmap()
                .load(data.coverUrl)
                .centerCrop()

                .into(cover)
            Glide.with(itemView.context)
                .load(data.icon).circleCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(author)
            if (data.title!=""){
                title.text=data.title
            }else{
                title.text=data.author
            }



    }
}
    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        if (holder is rvHolder) {
            holder.cover.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        }
    }
}











