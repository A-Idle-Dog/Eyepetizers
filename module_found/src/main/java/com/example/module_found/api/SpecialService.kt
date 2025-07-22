package com.example.module_found.api

import retrofit2.http.GET
import com.example.module_found.bean.SpecialBean
import com.example.module_found.bean.SpecialDetailBean
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
interface SpecialService {
    @GET("api/v3/specialTopics")
    suspend fun getSpecial(): SpecialBean
    @GET("api/v3/lightTopics/internal/{id}")
    suspend fun getSpecialDetail(@Path("id")id:String):SpecialDetailBean
    @GET("api/v3/specialTopics")
    suspend fun getMoreSpecial(
        @Query("start") start: String,
        @Query("num") num: String
    ): SpecialBean
}