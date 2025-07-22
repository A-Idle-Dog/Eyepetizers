package com.example.module_square.api

import com.example.module_square.bean.ChildTabBean
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
interface ChildTabService {
    @GET("api/v7/tag/childTab/{id}")
    suspend fun getChildTab(
        @Path("id") id: String,
        @Query("isRecTab") isRecTab: String,
        @Query("start") start: String,
        @Query("num") num: String
    ): ChildTabBean
}