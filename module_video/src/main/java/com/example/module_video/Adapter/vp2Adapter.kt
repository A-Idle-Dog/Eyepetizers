package com.example.module_video.Adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleEventObserver
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.module_video.model.invokeitem
import com.example.module_video.videoFragment
import java.lang.ref.WeakReference

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val data: List<invokeitem>
) : FragmentStateAdapter(fragmentActivity) {


    override fun getItemCount(): Int = data.size

    override fun createFragment(position: Int): Fragment {
        return videoFragment.newInstance(data[position])
    }


}