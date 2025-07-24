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
    private var touchX = 0f
    private var touchY = 0f
    private var isHorizontalScroll = false
    private val touchSlop = ViewConfiguration.get(context).scaledTouchSlop

    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                touchX = e.x
                touchY = e.y
                parent.requestDisallowInterceptTouchEvent(true)
                isHorizontalScroll = false
            }
            MotionEvent.ACTION_MOVE -> {
                if (!isHorizontalScroll) {
                    val dx = e.x - touchX
                    val dy = e.y - touchY
                    if (abs(dx) > touchSlop || abs(dy) > touchSlop) {
                        if (abs(dx) > abs(dy)*1.1) {
                            isHorizontalScroll = true
                            parent.requestDisallowInterceptTouchEvent(false)
                            return false
                        }
                    }
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                isHorizontalScroll = false
                parent.requestDisallowInterceptTouchEvent(false)
            }
        }
        return super.onInterceptTouchEvent(e)
    }
}

