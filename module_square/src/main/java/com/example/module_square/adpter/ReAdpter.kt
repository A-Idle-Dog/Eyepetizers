package com.example.module_square.adpter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.module_square.bean.Rec
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
    /*private var mInitBanner: ((reAdpter) -> Unit)? = null
    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> 0
            else -> 1
        }
    }
    fun onInitBanner(ir: (reAdpter) -> Unit) {
        mInitBanner = ir
    }*/

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position) ?: return
        if (holder is rvHolder) {
            holder.bind(item)
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder {
       /* when(viewType){
            0->{
                val binding=ItemBannerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                val vp=binding.bannerViewPager
                mInitBanner?.invoke(this@reAdpter)
                return BannerViewHolder(binding)
            }else->{*/
                val binding=ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return rvHolder(binding)
            //}
        }
    //inner class BannerViewHolder(bannerBinding: ItemBannerBinding):RecyclerView.ViewHolder(bannerBinding.root)
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
        init {
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
                       .navigation()
                }
            }
        }
    }
}











