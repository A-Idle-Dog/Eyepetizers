package com.example.module_square

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib.BaseActivity
import com.example.module_square.adpter.SqReAdpter
import com.example.module_square.databinding.ActivitySquareBinding
import com.example.module_square.viewmodel.JumpViewModle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class SquareActivity : BaseActivity<ActivitySquareBinding>() {
    private lateinit var jpVm :JumpViewModle
    private val mAdpter :SqReAdpter by lazy { SqReAdpter() }
    private val id :String by lazy {
        intent.getStringExtra("id").toString()
    }
    override fun getBinding(): ActivitySquareBinding {
        return ActivitySquareBinding.inflate(layoutInflater)
    }

    companion object {
        fun startActivity(id:String, context: Context, name:String){
            val intent = Intent(context,SquareActivity::class.java).apply{
                putExtra("id",id)
                putExtra("name",name)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        jpVm= ViewModelProvider(this)[JumpViewModle::class.java]
        mBinding.tvTitle.text =intent.getStringExtra("name").toString()
        mBinding.rvList.let {
            it.layoutManager= LinearLayoutManager(this)
            it.adapter=mAdpter
        }
        getChildData(id)
    }
    private fun getChildData(id: String) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                jpVm.getChildTabData(id).collectLatest {
                    mAdpter.submitData(it)
                }
            }
        }
    }

}
