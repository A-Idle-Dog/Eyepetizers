package com.example.module_found

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib.BaseActivity
import com.example.module_found.adpter.RvAllAdpter
import com.example.module_found.databinding.ActivitySpecialBinding
import com.example.module_found.viewmodel.SpecialViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SpecialActivity : BaseActivity<ActivitySpecialBinding>() {
    private lateinit var mvSp: SpecialViewModel
    private val mAdpter :RvAllAdpter by lazy { RvAllAdpter() }
    override fun getBinding(): ActivitySpecialBinding {
        return ActivitySpecialBinding.inflate(layoutInflater)
    }
    companion object{
        fun startSpecial(context: Context?){
            val intent =Intent(context,SpecialActivity::class.java)
            context?.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mvSp = ViewModelProvider(this)[SpecialViewModel::class.java]
        mBinding.reSpecialAll.apply {
            layoutManager=LinearLayoutManager(context.applicationContext)
            adapter=mAdpter
        }
        getData()
    }
    private fun getData(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                mvSp.getSpecialMore().collectLatest{
                    mAdpter.submitData(it)
                }
            }
        }
    }
}
