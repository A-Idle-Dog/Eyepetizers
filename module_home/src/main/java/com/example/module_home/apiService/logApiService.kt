package com.example.module_home.apiService

import com.example.module_home.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface logApiservice{
    @GET("/api/v5/index/tab/feed")
    suspend fun getFirst(): ApiResponse

    @GET
    suspend fun getNextPage(@Url url: String): ApiResponse
}