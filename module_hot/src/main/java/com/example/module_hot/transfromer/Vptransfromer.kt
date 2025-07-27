package com.example.module_hot.transfromer

import android.view.View
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class Vptransfromer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.apply {
            val pagewidth=width
            val pageheight=height
            val rotation = position * -30f
            pivotX = if (position < 0) pagewidth.toFloat() else 0f
            pivotY = pageheight.toFloat() / 2f

            rotationY = rotation
            alpha = if (position < -1 || position > 1) 0f else 1f
        }


    }
}