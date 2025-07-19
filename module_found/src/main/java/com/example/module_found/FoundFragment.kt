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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class FoundFragment :BaseFragment<FragmentFoundBinding>() {
    private lateinit var mvCate:CotegoryViewModel
    private var cateList =mutableListOf<CategoryBean>()
    private val mAdpter :RvCateAdpter by lazy {
        cateList?.let { RvCateAdpter(it) }!!
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFoundBinding {
        return FragmentFoundBinding.inflate(inflater,container,false)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return mBinding?.root?:super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mvCate = ViewModelProvider(requireActivity())[CotegoryViewModel::class.java]
        val gridLayoutManager =GridLayoutManager(this.context,3)
        mBinding?.rvClassify?.layoutManager=gridLayoutManager
        /**mBinding?.rvClassify?.apply {
            layoutManager=gridLayoutManager
            addItemDecoration(object :ItemDecoration(){
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    val spacing = dpToPx(2)
                    val poistion = parent.getChildAdapterPosition(view)
                    val colum = poistion%3

                    outRect.left = if (colum==0) 0 else spacing/2
                    outRect.right = if (colum==2) 0 else spacing/2

                    outRect.top = if (colum<3) 0 else spacing
                }
            })
        }*/
        getCategory()
    }
    private fun getCategory(){
        viewLifecycleOwner.lifecycleScope.launch {
            mvCate.getCategoryData()
            mvCate.categoryStateFlow.collect{
                it?.let {
                    cateList.clear()
                    cateList.addAll(it)
                    mBinding?.rvClassify?.adapter=mAdpter
                }
            }

        }

    }
    private fun dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            resources.displayMetrics
        ).toInt()
    }
}



