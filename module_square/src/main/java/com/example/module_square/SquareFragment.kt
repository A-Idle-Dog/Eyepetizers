package com.example.module_square

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.lib.BaseFragment
import com.example.module_square.adpter.reAdpter
import com.example.module_square.databinding.FragmentSquareBinding
import com.example.module_square.viewmodel.SquareViewModule
import kotlinx.coroutines.launch


class SquareFragment : BaseFragment<FragmentSquareBinding>() {
    private lateinit var squareViewModule :SquareViewModule
    private var staggeredGridState: Parcelable? = null
    private var firstLoad=true
    private val mAdpter :reAdpter by lazy {
        reAdpter()
    }



    override fun getBinding(inflater: LayoutInflater,container: ViewGroup?): FragmentSquareBinding {
        return FragmentSquareBinding.inflate(inflater,container,false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return mBinding?.root ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        staggeredGridState=mBinding?.rvCom?.layoutManager?.onSaveInstanceState()
        outState.putBoolean("First",firstLoad)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        squareViewModule= ViewModelProvider(requireActivity())[SquareViewModule::class.java]
        if (staggeredGridState!=null){
            firstLoad= savedInstanceState!!.getBoolean("First",true)
            staggeredGridState= savedInstanceState.getParcelable("staggeredGridState")

        }
        if(!firstLoad&&staggeredGridState!=null){
            mBinding?.rvCom?.layoutManager?.onRestoreInstanceState(staggeredGridState)
        }
        initRecycleView()
        if (firstLoad){
            getData()
            firstLoad=false
        }

    }
    private fun initRecycleView(){
        val layoutManager =  StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        mBinding?.rvCom?.itemAnimator?.changeDuration = 0
        mBinding?.rvCom?.layoutManager= layoutManager
        mBinding?.rvCom?.adapter=mAdpter
        mBinding?.rvCom?.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState:Int){
                super.onScrollStateChanged(recyclerView, newState)
                // 当滚动停止时检查并修正布局
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    layoutManager.invalidateSpanAssignments()
                }
            }
        })

    }

    private fun getData(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                squareViewModule.getSquare().collect{
                    mAdpter.submitData(it)
                    }
                }

            }
        }
    }
