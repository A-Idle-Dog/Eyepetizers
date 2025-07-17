package com.example.module_square

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module_square.adpter.reAdpter
import com.example.lib.BaseFragment
import com.example.module_square.databinding.FragmentSquareBinding
import com.example.module_square.bean.TabListBean
import com.example.module_square.bean.ChildTabBean
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import com.example.lib.NetStatus
import com.example.module_square.viewmodel.SquareViewModule
import com.example.module_square.viewmodel.JumpViewModle


class SquareFragment : BaseFragment<FragmentSquareBinding>() {
    private lateinit var squareViewModule :SquareViewModule
    private lateinit var jumpViewModle: JumpViewModle
    private var tabList :TabListBean? = null
    private  var childList = mutableListOf<ChildTabBean>()
    private val mAdapter : reAdpter by lazy {
        tabList?.let { reAdpter(it,childList) }!!
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        squareViewModule= ViewModelProvider(requireActivity())[SquareViewModule::class.java]
        jumpViewModle= ViewModelProvider(requireActivity())[JumpViewModle::class.java]
        mBinding?.rvCom?.layoutManager= LinearLayoutManager(activity)
        getChildData()
        aboutLoad()
    }
    fun getChildData(){
        viewLifecycleOwner.lifecycleScope.launch {
            jumpViewModle.getChildData()
            jumpViewModle.childTabStateFlow.collect{
                it?.let {
                    childList.clear()
                    childList.addAll(it)
                    if (childList.isNotEmpty()){
                        getTabData()
                    }else{
                        Log.w("SquareFragment", "childList 为空，不加载 tabList")
                    }

                }
            }
        }
    }
    fun getTabData(){
        lifecycleScope.launch {
            squareViewModule.getTabData()
            repeatOnLifecycle(Lifecycle.State.STARTED){
                squareViewModule.tabStateFlow.collect{
                    if (it!=null){
                        mBinding?.rvCom?.apply {
                            tabList = it
                            adapter = mAdapter
                        }
                    }
                }

            }
        }


    }
    fun aboutLoad(){
        squareViewModule.netState.observe(viewLifecycleOwner){
            when(it){
                NetStatus.LOADING->mBinding?.pbLoad?.visibility=View.VISIBLE
                NetStatus.SUCCESS->{mBinding?.pbLoad?.visibility=View.GONE
                    Toast.makeText(this.requireContext(),"加载成功", Toast.LENGTH_SHORT).show()
                }
                else->{mBinding?.pbLoad?.visibility=View.GONE
                    Toast.makeText(this.requireContext(),"加载失败", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}