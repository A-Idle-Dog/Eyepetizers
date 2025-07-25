package com.example.module_home

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class CustomRecyclerView@JvmOverloads constructor (
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.recyclerview.R.attr.recyclerViewStyle
) : RecyclerView(context, attrs, defStyleAttr){
    private val touch=ViewConfiguration.get(context).scaledTouchSlop
    //起始位置
    private var startX = 0f
    private var startY = 0f
    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                // 记录起始触摸位置
                startX = e.x
                startY = e.y
                // 通知父控件暂时不要拦截触摸事件
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                // 计算滑动距离
                val dx = e.x - startX
                val dy = e.y - startY

                // 判断滑动方向（水平或垂直）
                val isHorizontalScroll = abs(dx) > abs(dy) && abs(dx) > touch
                abs(dy) > abs(dx) && abs(dy) > touch
                if(isHorizontalScroll){
                    //允许父类处理
                    parent.requestDisallowInterceptTouchEvent(false)
                    return false
                }else{
                    //自己处理
                    parent.requestDisallowInterceptTouchEvent(true)
                }

            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                // 触摸结束，允许父控件拦截
                parent.requestDisallowInterceptTouchEvent(false)
            }

        }
        return super.onInterceptTouchEvent(e)

    }

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        if (e?.action  ==MotionEvent.ACTION_MOVE){
            val dx= (e.x).minus(startX)
            val dy= (e.y).minus(startY)
            val isHorizontal= abs(dx) > abs(dy) && abs(dx) >touch
            if (isHorizontal){
                return false
            }
        }
        return super.onTouchEvent(e)
    }
}

