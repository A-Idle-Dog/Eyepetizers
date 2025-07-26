package com.example.module_my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.module_my.Adapter.tabAdapter
import com.example.module_my.ViewModel.FavoritesViewModel
import com.example.module_my.databinding.FragmentMineBinding  // 确保导入了正确的 binding 类
import com.google.android.material.tabs.TabLayoutMediator

class mine : Fragment() {
    private var _binding: FragmentMineBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabAdapter()
        initView()
    }

    fun initTabAdapter(){
        binding.viewPager.adapter = tabAdapter(requireActivity())
        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position->
            tab.text= when(position){
                0->"喜欢"
                1->"收藏"
                else->"喜欢"
            }
        }.attach()
    }

    fun initView(){
        Glide.with(binding.ivAvatar.context)
            .load(R.drawable.touxiang)
            .circleCrop()
            .into(binding.ivAvatar)
        binding.tvUsername.text = "过段时间"
        binding.tvDescription.text = "这个人很懒，什么描述也没有"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = mine()
    }
}