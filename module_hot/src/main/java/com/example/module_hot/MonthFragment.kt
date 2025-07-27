package com.example.module_hot

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib.BaseFragment
import com.example.lib.NetControl
import com.example.lib.net.NetWork
import com.example.module_hot.adpter.RvAdpter
import com.example.module_hot.databinding.FragmentMonthBinding
import com.example.module_hot.viewmodel.HotViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.module_hot.MyRecycle


class MonthFragment() : BaseFragment<FragmentMonthBinding>() {
    private lateinit var vmHot :HotViewModel
    private lateinit var type: String
    private var linearLayoutManager: LinearLayoutManager? = null
    private val mAdpter :RvAdpter by lazy {
        RvAdpter()
    }
    private var hasShownNetworkError = false
    private lateinit var netControl: NetControl

    companion object{
        fun new(type:String)=MonthFragment().apply {
            arguments = Bundle().apply {
                putString("type",type)
            }
        }
    }

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
        //type = savedInstanceState?.getString("type") ?: arguments?.getString("type") ?: return
        Log.d("MonthFragment", "onViewCreated: 视图创建成功")


        vmHot=ViewModelProvider(requireActivity())[HotViewModel::class.java]
        mBinding?.rvMon?.layoutManager =LinearLayoutManager(requireContext())
        mBinding?.rvMon?.adapter=mAdpter
        type = arguments?.getString("type") ?: return
        netControl = NetControl(requireContext())
        connect()
        if (savedInstanceState == null) {
            getHot(type)
        }




    }
    private fun getHot(type:String){
        viewLifecycleOwner.lifecycleScope.launch {
            vmHot.getHot(type)
            vmHot.getHotStateFlow(type).collectLatest { result ->
                result?.itemList?.let { items ->
                    mAdpter.submitList(items.toList()){
                        val savedPosition = vmHot.getPosition(type)
                        if (savedPosition!=null){
                            linearLayoutManager?.onRestoreInstanceState(savedPosition)
                        }else{
                            mBinding?.rvMon?.scrollToPosition(0)
                        }
                    }
                }
            }

        }
    }
    private fun connect(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // 观察当前 type 对应的网络状态
                netControl.isConnected.collect { isConnected ->
                    when (isConnected) {
                        false -> { // 网络错误
                            if (!NetWork.hasShownNetworkError()) {
                                Toast.makeText(
                                    requireContext(),
                                    "网络连接失败，请检查网络",
                                    Toast.LENGTH_SHORT
                                ).show()
                                NetWork.setShownNetworkError()
                            }
                        }
                        else -> { NetWork.resetNetworkErrorState()
                            getHot(type)}
                    }
                }
            }
        }

    }
    override fun onPause() {
        super.onPause()
        val currentPosition = linearLayoutManager?.onSaveInstanceState()
        if (currentPosition != null) {
            vmHot.savePosition(type, currentPosition)
        }
    }


}