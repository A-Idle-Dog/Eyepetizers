package com.example.module_hot.retrofit

import com.example.lib.net.Retrofit
import com.example.module_hot.api.HotService

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
object HotNet {
    val hot:HotService=Retrofit.getService(HotService::class.java)
}