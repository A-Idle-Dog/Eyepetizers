package com.example.module_video.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.module_video.fragment.commentFragment
import com.example.module_video.fragment.profileFragment
import com.example.module_video.model.invokeitem

class tabAdapter(fg : FragmentActivity,private val data: invokeitem) : FragmentStateAdapter(fg){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> profileFragment.newInstance(data)
            else -> commentFragment.newInstance(data)
        }
    }
}
