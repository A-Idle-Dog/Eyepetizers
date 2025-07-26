package com.example.module_my.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.module_my.CollectFragment
import com.example.module_my.LikeFragment

class tabAdapter(fg : FragmentActivity): FragmentStateAdapter(fg) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LikeFragment()
            1 -> CollectFragment()
            else -> LikeFragment()
        }
    }
}