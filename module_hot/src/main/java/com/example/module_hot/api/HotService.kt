package com.example.module_hot.api

import com.example.module_hot.bean.HotBean
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
interface HotService {
    @GET("/api/v4/rankList/videos")
    suspend fun getHot(@Query("strategy")strategy:String):HotBean
}