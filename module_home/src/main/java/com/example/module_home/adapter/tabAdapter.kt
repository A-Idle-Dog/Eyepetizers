package com.example.module_home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.module_home.homeLogFragment
import com.example.module_home.homeRecommendFragment

class fragmentadapter(fg: FragmentActivity) : FragmentStateAdapter(fg) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> homeRecommendFragment()
            1 -> homeLogFragment()
            else -> homeRecommendFragment()
        }
    }
}