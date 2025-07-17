package com.example.module_home.apiService

import com.example.module_home.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface logApiservice{
    @GET("/api/v5/index/tab/feed")
        suspend fun getlogDate(@Query("page")page:Int ): ApiResponse
}