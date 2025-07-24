package com.example.module_found.adpter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.lib.time
import com.example.module_found.R
import com.example.module_found.bean.Item
import com.example.module_found.databinding.ItemCateDetailBinding

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class RvCateDetailAdpter:PagingDataAdapter<Item,RvCateDetailAdpter.rvCateDetailHolder>(object :DiffUtil.ItemCallback<Item>(){
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem==newItem
    }

}) {

    inner class rvCateDetailHolder(private val binding:ItemCateDetailBinding):RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener {
                val position=bindingAdapterPosition
                if (position!=RecyclerView.NO_POSITION){
                    val cate =getItem(bindingAdapterPosition)
                    cate?.data?.content?.data?.consumption?.let { it1 ->
                        ARouter.getInstance()
                            .build("/module_video/VideoActivity")
                            .withString("playuri", cate.data.content.data.playUrl)
                            .withString("cover", cate.data.content.data.cover?.feed)
                            .withInt("uid", cate.data.content.data.id.toInt())//000
                            .withString("title",cate.data.content.data.title)
                            .withString("author",cate.data.content.data.author?.name)
                            .withString("authoricon",cate.data.header.icon)
                            .withString("tags", cate.data.content.data.tags?.get(0)?.name)
                            .withString("des",cate.data.content.data.description)
                            .withInt("likecount", it1.collectionCount!!)
                            .withInt("collectcount",cate.data.content.data.consumption.realCollectionCount!!)
                            .withBoolean("isLike", cate.data.content.data.collected)
                            .withBoolean("isCollect", cate.data.content.data.reallyCollected)
                            .withString("shareUrl", cate.data.content.data.webUrl?.raw)
                            .navigation()
                    }
                }
            }
            binding.ivShare.setOnClickListener {
                val data = getItem(bindingAdapterPosition)
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    "是我喜欢地视频，直接观看！\n${
                        data!!.data.content.data.playUrl?.replace(
                            "http",
                            "https"
                        )
                    }"
                )
                itemView.context.startActivity(Intent.createChooser(intent, "分享到"))
            }

        }


        fun bind(item: Item?){
            binding.apply {
                tvName.text=item?.data?.header?.title?: "未知作者"
                tvTime.text=item?.data?.content?.data?.duration?.time()?: "0:00"
                tvDesVideo.text=item?.data?.content?.data?.description?: "暂无描述"
                tvAclassify.text="#"+item?.data?.content?.data?.category
                if (item != null) {
                    tvCount.text= item.data.content.data.consumption?.playCount.toString()
                }
                val imUrl1 = item?.data?.header?.icon?.replace("http://","https://")
                val imUrl2 = item?.data?.content?.data?.cover?.feed?.replace("http://","https://")
                Glide.with(itemView)
                    .load(imUrl1)
                    .placeholder(R.drawable.loading2)
                    .apply(RequestOptions.circleCropTransform())
                    .into(ivAuthor)
                Glide.with(itemView)
                    .load(imUrl2)
                    .placeholder(R.drawable.loading2)
                    .into(ivVideo)
            }
        }
    }

    override fun onBindViewHolder(holder: rvCateDetailHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rvCateDetailHolder {
        val binding = ItemCateDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return rvCateDetailHolder(binding)
    }
}