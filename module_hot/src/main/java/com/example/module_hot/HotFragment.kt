package com.example.module_hot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lib.BaseFragment
import com.example.module_hot.adpter.RvAdpter
import com.example.module_hot.adpter.VpAdpter
import com.example.module_hot.databinding.FragmentHotBinding
import com.example.module_video.model.Item
import com.google.android.material.tabs.TabLayoutMediator


class HotFragment : BaseFragment<FragmentHotBinding>() {
    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHotBinding {
        return FragmentHotBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_hot, container, false)
        return mBinding?.root ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adpter = VpAdpter(this)
        mBinding?.let {
            it.vpHot.isSaveEnabled = false
            if (it?.vpHot?.adapter==null){
                it?.vpHot?.adapter =adpter
            }
            TabLayoutMediator(it.tabHot, it.vpHot) { tab, position ->
                tab.text = adpter.nameList[position]
            }.attach()
        }
    }


}