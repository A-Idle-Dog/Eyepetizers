package com.example.module_hot

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib.BaseFragment
import com.example.module_hot.adpter.RvAdpter
import com.example.module_hot.databinding.FragmentMonthBinding
import com.example.module_hot.viewmodel.HotViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MonthFragment() : BaseFragment<FragmentMonthBinding>() {
    private lateinit var vmHot :HotViewModel
    private val mAdpter :RvAdpter by lazy {
        RvAdpter()
    }
    companion object{
        fun new(type:String)=MonthFragment().apply {
            arguments = Bundle().apply {
                putString("type",type)
            }
        }
    }
    /*override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // 存入一个标记，确保系统不销毁该 Fragment
        outState.putBoolean("KEY_KEEP_ALIVE", true)
    }*/

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMonthBinding {
        return FragmentMonthBinding.inflate(inflater, container, false)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_month, container, false)
        return mBinding?.root ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MonthFragment", "onViewCreated: 视图创建成功")
        vmHot=ViewModelProvider(this)[HotViewModel::class.java]
        mBinding?.rvMon?.layoutManager =LinearLayoutManager(this.context)
        mBinding?.rvMon?.adapter=mAdpter
        val type = arguments?.getString("type") ?: return
        if (savedInstanceState == null) {
            getHot(type)
        }


    }
    private fun getHot(type:String){
        viewLifecycleOwner.lifecycleScope.launch {
            vmHot.getHot(type)
            vmHot.hotStateFlow.collectLatest { result ->
                result?.itemList?.let { items ->
                    mAdpter.submitList(items.toList())
                }
            }

        }
    }


}