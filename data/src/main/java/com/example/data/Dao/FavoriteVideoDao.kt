package com.example.data.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.Bean.FavoriteVideo

@Dao
interface FavoriteVideoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(video: FavoriteVideo)

    @Delete
    suspend fun delete(video: FavoriteVideo)

    @Query("DELETE FROM favorite_videos WHERE videoId = :videoId")
    suspend fun deleteByVideoId(videoId: Int)

    @Query("SELECT * FROM favorite_videos ORDER BY created_At DESC")
    fun getAllFavorites(): LiveData<List<FavoriteVideo>>

    @Query("SELECT * FROM favorite_videos WHERE videoId = :videoId")
    suspend fun getFavoriteByVideoId(videoId: Int): FavoriteVideo?
}