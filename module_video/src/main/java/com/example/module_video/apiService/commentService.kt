package com.example.module_video.apiService

import com.example.module_video.model.CommentResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface commentService{
    @GET("api/v2/replies/video?")
    suspend fun getCommentData(@Query("videoId") videoId:Int): CommentResponse
}