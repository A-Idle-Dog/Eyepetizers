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
                    // 列宽 = ImageView宽度（因cover是match_parent，宽度等于列宽）
                    columnWidth = cover.width
                    // 若首次布局时已有数据，重新绑定一次（确保高度正确）
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
                        .navigation()
                }
            }
        }

        @SuppressLint("SuspiciousIndentation")
        fun bind(data:Squarepic){
            currentData = data
            //currentUrl=data.coverUrl
            // 清空旧图片
            //cover.setImageDrawable(null)
            // 重置高度
            //cover.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT

            //cover.post {
                // 校验：避免View复用后，旧的post回调影响新数据
                //if (currentUrl != data.coverUrl) return@post

                //val columnWidth = cover.width // 列宽（match_parent后等于ImageView宽度）
                if (columnWidth <= 0) return//@post // 宽度未测量完成，跳过

                val targetHeight = if (data.picWidth > 0 && data.picHight > 0) {
                    (data.picHight.toFloat() / data.picWidth * columnWidth).toInt()
                } else {
                    // 无宽高信息时，用默认比例（如1:1）避免高度为0
                    columnWidth // 默认正方形
                }
                cover.layoutParams.height = targetHeight

            Glide.with(itemView.context)
                .asBitmap()
                .load(data.coverUrl)
                .centerCrop()
                /*.apply(RequestOptions().fitCenter())
                .listener(object :RequestListener<Bitmap>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        cover.layoutParams.height = columnWidth
                        cover.setImageResource(R.drawable.loading2) // 错误占位图
                        Log.e("ImageLoad", "图片加载失败: ${e?.message}")
                        return false

                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (currentUrl != data.coverUrl) return false

                        // 计算高度：高度 = (图片高度 / 图片宽度) * 列宽（保持比例）
                        val targetHeight = ((resource?.height?.toFloat()  )!! / resource.width * columnWidth).toInt()
                        cover.layoutParams.height = targetHeight // 设置动态高度
                        cover.setImageBitmap(resource) // 显示图片
                        return true
                    }
                })*/
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

       // }

    }
}
    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        if (holder is rvHolder) {
            holder.cover.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        }
    }
}











