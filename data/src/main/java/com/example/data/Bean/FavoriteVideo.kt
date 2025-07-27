package com.example.data.Bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_videos")
data class FavoriteVideo(
    val palyurl :String,
    val cover : String,
    @PrimaryKey val videoId: Int,
    val title: String,
    val author: String,
    val authoricon: String,
    val tags: String,
    val des:String,
    val likecount: Int,
    val collectcount: Int,
    var isLike: Boolean,
    var isCollect: Boolean,
    val shareUrl: String,
    val source: Int,
    val currentPosition: Int,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis()
)