package com.example.module_found

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib.BaseFragment
import com.example.lib.NetControl
import com.example.lib.net.NetWork
import com.example.module_found.adpter.RvCateAdpter
import com.example.module_found.adpter.RvSpAdpter
import com.example.module_found.bean.CategoryBean
import com.example.module_found.bean.SpecialDetailBean
import com.example.module_found.databinding.FragmentFoundBinding
import com.example.module_found.viewmodel.CotegoryViewModel
import com.example.module_found.viewmodel.SpecialViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext


class FoundFragment :BaseFragment<FragmentFoundBinding>() {
    private lateinit var netControl: NetControl
    private var hasShownNetworkError = false
    private lateinit var mvCate: CotegoryViewModel
    private lateinit var mvSp: SpecialViewModel
    private var cateList = mutableListOf<CategoryBean>()
    private var specialList = mutableListOf<SpecialDetailBean>()
    private val mAdpter: RvCateAdpter by lazy {
        RvCateAdpter(cateList)
    }
    private val mAdpter1: RvSpAdpter by lazy {
        RvSpAdpter(specialList)
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFoundBinding {
        return FragmentFoundBinding.inflate(inflater, container, false)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return mBinding?.root ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mvCate = ViewModelProvider(requireActivity())[CotegoryViewModel::class.java]
        mvSp = ViewModelProvider(requireActivity())[SpecialViewModel::class.java]
        val gridLayoutManager = GridLayoutManager(this.context, 3)
        mBinding?.rvClassify?.layoutManager = gridLayoutManager
        mBinding?.rvSpecial?.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        netControl = NetControl(requireContext())
        connect()
        getSpecial()
        getCategory()
        mBinding?.imJump?.setOnClickListener {
            SpecialActivity.startSpecial(this.context)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getCategory() {
        viewLifecycleOwner.lifecycleScope.launch {
            mvCate.getCategoryData()
            mvCate.categoryStateFlow.collect {
                it?.let {
                    cateList.clear()
                    cateList.addAll(it)
                    if (mBinding?.rvClassify?.adapter == null) {
                        mBinding?.rvClassify?.adapter = mAdpter
                    } else {
                        mAdpter.notifyDataSetChanged() // 刷新
                    }
                }
            }

        }

    }

    private fun getSpecial() {
        viewLifecycleOwner.lifecycleScope.launch {
            mvSp.getSpecial()
            mvSp.specialStateFlow.collect {
                it?.let {
                    specialList.clear()
                    specialList.addAll(it)
                    mBinding?.rvSpecial?.adapter = mAdpter1
                }
            }
        }
    }

    private fun connect() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                netControl.isConnected.collect { isConnected ->
                    when (isConnected) {
                        false -> {
                            if (!NetWork.hasShownNetworkError()) {
                                Toast.makeText(
                                    context,
                                    "网络连接失败，请检查网络",
                                    Toast.LENGTH_SHORT
                                ).show()
                                NetWork.setShownNetworkError()
                            }
                        }

                        else -> {
                            NetWork.resetNetworkErrorState()
                            getSpecial()
                            getCategory()
                        }
                    }
                }
            }
        }
    }
        override fun onDestroyView() {
            super.onDestroyView()
            mBinding?.rvClassify?.setAdapter(null)
        }
    }




