package com.example.module_hot.adpter

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.module_hot.MonthFragment

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class VpAdpter(fragment:Fragment):FragmentStateAdapter(fragment.childFragmentManager, fragment.lifecycle) {
    private val typeList = listOf( "weekly","monthly", "historical")
    val nameList= listOf("周榜","月榜","总榜")
    private val typeToIdMap = mapOf(
        "weekly" to 1001L,    // 手动指定，确保唯一
        "monthly" to 1002L,
        "historical" to 1003L
    )



    override fun getItemCount(): Int =typeList.size

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->MonthFragment.new(typeList[0])
            1->MonthFragment.new(typeList[1])
            2->MonthFragment.new(typeList[2])
            else ->throw IllegalArgumentException("无")
        }
    }
    override fun getItemId(position: Int): Long {
        return typeToIdMap[typeList[position]]
            ?: throw IllegalArgumentException("Invalid type at position $position")
    }

    // 检查 ID 是否存在于映射中
    override fun containsItem(itemId: Long): Boolean {
        return typeToIdMap.values.contains(itemId)
    }

}