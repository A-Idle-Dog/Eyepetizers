package com.example.module_found

import android.graphics.Outline
import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.lib.BaseFragment
import com.example.module_found.adpter.RvCateAdpter
import com.example.module_found.viewmodel.CotegoryViewModel
import com.example.module_found.bean.CategoryBean
import com.example.module_found.databinding.FragmentFoundBinding
import android.util.TypedValue
import android.view.ViewTreeObserver
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module_found.adpter.RvSpAdpter
import com.example.module_found.bean.Banner2Item
import com.example.module_found.bean.SpecialBean
import com.example.module_found.bean.SpecialDetailBean
import com.example.module_found.viewmodel.SpecialViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch



class FoundFragment :BaseFragment<FragmentFoundBinding>() {
    private lateinit var mvCate: CotegoryViewModel
    private lateinit var mvSp: SpecialViewModel
    private var cateList = mutableListOf<CategoryBean>()
    private  var specialList= mutableListOf<SpecialDetailBean>()
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
        mvSp=ViewModelProvider(requireActivity())[SpecialViewModel::class.java]
        val gridLayoutManager = GridLayoutManager(this.context, 3)
        mBinding?.rvClassify?.layoutManager = gridLayoutManager
        mBinding?.rvSpecial?.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        getSpecial()
        getCategory()
        mBinding?.imJump?.setOnClickListener{
            SpecialActivity.startSpecial(this.context)
        }
    }

    private fun getCategory() {
        viewLifecycleOwner.lifecycleScope.launch {
            mvCate.getCategoryData()
            mvCate.categoryStateFlow.collect {
                it?.let {
                    cateList.clear()
                    cateList.addAll(it)
                    mBinding?.rvClassify?.adapter = mAdpter
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
}




