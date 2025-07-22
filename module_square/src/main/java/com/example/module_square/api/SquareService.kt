package com.example.module_square.api

import com.example.module_square.bean.SquareBean
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
interface SquareService {
    @GET("api/v7/community/tab/rec")
    suspend fun getSquareMore(
        @Query("startScore")startScore:String,
        @Query("pageCount")pageCount:String
    ):SquareBean
}