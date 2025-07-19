package com.example.module_video.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.module_video.fragment.commentFragment
import com.example.module_video.fragment.profileFragment

class tabAdapter(fg : FragmentActivity) : FragmentStateAdapter(fg){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> profileFragment()
            else -> commentFragment()
        }
    }
}
