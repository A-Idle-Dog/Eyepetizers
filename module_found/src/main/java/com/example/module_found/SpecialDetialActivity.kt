package com.example.module_found

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lib.BaseActivity
import com.example.module_found.adpter.RvSpDetailAdpter
import com.example.module_found.databinding.ActivitySpecialDetialBinding
import com.example.module_found.databinding.ItemEndBinding
import com.example.module_found.viewmodel.SpecialViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SpecialDetialActivity : BaseActivity<ActivitySpecialDetialBinding>() {
    private lateinit var vmSpecial: SpecialViewModel



    companion object {

        fun actionStart(context: Context, id: String,des:String, imgUrl: String, imgSpecial: ImageView) {

            val intent = Intent(context, SpecialDetialActivity::class.java).apply {
                putExtra("id", id)
                putExtra("imgUrl", imgUrl)
                putExtra("des",des)
            }

            val option = ActivityOptions.makeSceneTransitionAnimation(
                context as Activity,
                imgSpecial,
                imgSpecial.transitionName
            )

            context.startActivity(intent, option.toBundle())
        }
    }

    override fun getBinding(): ActivitySpecialDetialBinding {
        return ActivitySpecialDetialBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.rvClassifyDetail.layoutManager = LinearLayoutManager(this)
        vmSpecial = ViewModelProvider(this)[SpecialViewModel::class.java]
        initView()
    }
    private fun initView(){
        setSupportActionBar(mBinding.toolDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val id: String = intent.getStringExtra("id") ?: ""
        val des:String=intent.getStringExtra("des")?:""
        val imgVideoUrl: String = intent.getStringExtra("imgUrl") ?: ""
        if (id.isEmpty()) {
            Toast.makeText(this@SpecialDetialActivity,"无效id",Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        getData(id)
        mBinding.collDetail.title=des
        if (imgVideoUrl.isNotEmpty()) {
            Glide.with(this)
                .load(imgVideoUrl)
                .into(mBinding.imDetail)
        }
        mBinding.swip.setOnRefreshListener {
            // 触发重新加载数据
            mBinding.swip.isRefreshing = true
            getData(id)
            mBinding.swip.isRefreshing = false
        }

        mBinding.btnUp.setOnClickListener {
            mBinding.rvClassifyDetail.smoothScrollToPosition(0)
        }


    }

    private fun getData(id: String) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vmSpecial.getSpecialData(id)
                vmSpecial.specialDetialStateFlow.collectLatest {
                    if (it != null) {
                        mBinding.rvClassifyDetail.adapter = RvSpDetailAdpter(it)
                        mBinding.tvDes.text = it.text ?: "暂无描述"
                    }
                }
            }

        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}