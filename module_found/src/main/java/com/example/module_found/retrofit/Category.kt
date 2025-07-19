package com.example.module_found.retrofit

import com.example.lib.net.Retrofit
import com.example.module_found.api.CategoryService
import com.example.module_found.api.SpecialService

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
object Category {
    val category:CategoryService = Retrofit.getService(CategoryService::class.java)
    val special:SpecialService =Retrofit.getService(SpecialService::class.java)
}