package com.example.module_square.api

import com.example.module_square.bean.TabListBean
import retrofit2.http.GET

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
interface TabService {
    @GET("api/v7/tag/tabList")
    suspend fun getCommunityTab(): TabListBean
}