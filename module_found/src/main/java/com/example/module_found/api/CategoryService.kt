package com.example.module_found.api

import com.example.module_found.bean.CategoryBean
import com.example.module_found.bean.CategoryDetailBean
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
interface CategoryService {
    @GET("api/v4/categories")
    suspend fun getCategory() :List<CategoryBean>

    @GET("api/v1/tag/videos")
    suspend fun getCategory(
        @Query("id")id:String?,
        @Query("start")start:String,
        @Query("num")num:String,
        @Query("strategy")strategy:String
    ) :CategoryDetailBean


}