package com.example.module_square

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lib.BaseFragment
import com.example.module_square.adpter.reAdpter
import com.example.module_square.databinding.FragmentSquareBinding
import com.example.module_square.viewmodel.SquareViewModule
import kotlinx.coroutines.launch


class SquareFragment : BaseFragment<FragmentSquareBinding>() {
    private lateinit var squareViewModule :SquareViewModule
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        squareViewModule= ViewModelProvider(this)[SquareViewModule::class.java]
        val layoutManager = GridLayoutManager(context,2)
        mBinding?.rvCom?.layoutManager= layoutManager
        mBinding?.rvCom?.adapter=mAdpter
        getData()

    }

    fun getData(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                squareViewModule.getSquare().collect{
                    mAdpter.submitData(it)
                    }
                }

            }
        }
    }
