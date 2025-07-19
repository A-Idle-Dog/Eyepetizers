package com.example.module_video.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.module_video.model.invokeitem
import com.example.module_video.videoFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val data: List<invokeitem>) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = data.size

    override fun createFragment(position: Int): Fragment {
        return videoFragment.newInstance(data[position])
    }
}