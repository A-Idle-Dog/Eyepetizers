package com.example.lib

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */

fun Int.time():String {
    val minute = this/60
    val second = this%60
    return if (second > 10) {
        "$minute:$second"
    } else {
        "$minute:0$second"
    }
}