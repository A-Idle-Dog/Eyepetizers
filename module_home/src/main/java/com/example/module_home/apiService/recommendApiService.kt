package com.example.module_home.apiService

import com.example.module_home.model.rApiResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface recommendApiservice{
    @GET("/api/v5/index/tab/allRec")
    suspend fun getrecommendDate(): rApiResponse

    @GET
    suspend fun getRecommendDate(@Url url: String): rApiResponse
}