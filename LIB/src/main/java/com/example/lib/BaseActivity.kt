package com.example.lib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
abstract class BaseActivity<viewBinding:ViewBinding> : AppCompatActivity() {
    val mBinding :viewBinding by lazy {
        getBinding()
    }
    abstract fun getBinding() : viewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        setContentView(mBinding.root)

    }
}