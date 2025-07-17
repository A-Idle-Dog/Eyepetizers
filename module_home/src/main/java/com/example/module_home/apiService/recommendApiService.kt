package com.example.module_home.apiService

import com.example.module_home.model.rApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface recommendApiservice{
    @GET("/api/v5/index/tab/allRec")
    suspend fun getrecommendDate(@Query("page")page: Int, @Query("per_page") perPage: Int): rApiResponse
}