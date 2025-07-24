package com.example.module_squa

import android.widget.Toast

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
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
        loadState()
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
        //禁用自动调整间隙
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
    private fun loadState(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                mAdpter.loadStateFlow.collect{
                    val refresh=it.refresh
                    when{
                        refresh is LoadState.Loading->{ mBinding?.pbLoad?.visibility = View.VISIBLE}
                        refresh is LoadState.NotLoading->{
                            mBinding?.pbLoad?.visibility  =View.GONE}
                        refresh is LoadState.Error ->{mBinding?.pbLoad?.visibility = View.GONE
                            val errorMsg = (refresh as LoadState.Error).error.message ?: "加载失败"
                            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()}
                    }
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        mBinding?.rvCom?.setAdapter(null)
    }
}
