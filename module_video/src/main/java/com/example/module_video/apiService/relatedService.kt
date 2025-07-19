package com.example.module_video.apiService

import com.example.module_video.model.Item
import com.example.module_video.model.OthersData
import retrofit2.http.GET
import retrofit2.http.Query

interface relatedService {
    @GET("api/v4/video/related?")
    suspend fun getRelatedData(@Query("id") id: Int): OthersData
}