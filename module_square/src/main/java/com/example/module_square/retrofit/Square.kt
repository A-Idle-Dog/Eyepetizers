package com.example.module_square.retrofit

import com.example.lib.net.Retrofit
import com.example.module_square.api.SquareService

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
object    Square {
    val squareService:SquareService=Retrofit.getService(SquareService::class.java)
}