package com.example.module_found

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.lib.BaseActivity
import com.example.module_found.adpter.RvCateDetailAdpter
import com.example.module_found.databinding.ActivityFoundBinding
import com.example.module_found.viewmodel.CotegoryViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class FoundActivity : BaseActivity<ActivityFoundBinding>() {
    private lateinit var vmCa :CotegoryViewModel
    private val mAdpter :RvCateDetailAdpter by lazy { RvCateDetailAdpter() }
    private val categoryId by lazy { intent.getStringExtra("id") ?: "" }
    override fun getBinding(): ActivityFoundBinding {
        return ActivityFoundBinding.inflate(layoutInflater)
    }
    companion object{
        fun startDetail(
            context: Context,
            id: String,
            desc: String,
            tvClassify: TextView,
            position: Int,
            url:String
        ){
            val intent = Intent(context,FoundActivity::class.java).apply {
                putExtra("id",id)
                putExtra("desc",desc)
                putExtra("tvClassify",tvClassify.text)
                putExtra("position",position)
                putExtra("url",url)
            }
            context.startActivity(intent)

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        vmCa = ViewModelProvider(this)[CotegoryViewModel::class.java]
        mBinding.rvClassifyDetail.apply {
            layoutManager=LinearLayoutManager(this@FoundActivity)
            adapter=mAdpter

        }
        initView()
        getData(categoryId)
    }
    private fun initView(){
        setSupportActionBar(mBinding.toolDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.collDetail.title=intent.getStringExtra("tvClassify")
        mBinding.tvDes.text=intent.getStringExtra("desc")
        /*val imUrl = intent.getStringExtra("url")?.replace("http://","https://")
        Glide.with(this)
            .load(imUrl)
            .placeholder(R.drawable.loading2)
            .into(mBinding.imDetail)*/

        mBinding.btnUp.setOnClickListener {
            mBinding.rvClassifyDetail.smoothScrollToPosition(0)
        }
        mBinding.swip.setOnRefreshListener {
            // 触发重新加载数据
            mBinding.swip.isRefreshing = true
            getData(categoryId)
            mBinding.swip.isRefreshing = false
        }

    }
    private fun getData(id: String){
        if (id.isNotEmpty()){
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED){
                    vmCa.getCateDetail(id).collectLatest {
                        mAdpter.submitData(it)
                    }
                }
            }
        }else{
            Toast.makeText(this, "数据加载失败，请重试", Toast.LENGTH_SHORT).show()}

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}