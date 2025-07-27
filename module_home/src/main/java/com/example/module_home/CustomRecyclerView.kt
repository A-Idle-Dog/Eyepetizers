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
    private var startX = 0f
    private var startY = 0f
    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = e.x
                startY = e.y
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = e.x - startX
                val dy = e.y - startY

                val isHorizontalScroll = abs(dx) > abs(dy) && abs(dx) > touch
                abs(dy) > abs(dx) && abs(dy) > touch
                if(isHorizontalScroll){
                    parent.requestDisallowInterceptTouchEvent(false)
                    return false
                }else{
                    parent.requestDisallowInterceptTouchEvent(true)
                }

            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
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

