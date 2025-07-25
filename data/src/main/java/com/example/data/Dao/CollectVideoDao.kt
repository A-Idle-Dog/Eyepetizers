package com.example.data.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.Bean.CollectVideo

@Dao
interface CollectVideoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(video: CollectVideo)

    @Delete
    suspend fun delete(video: CollectVideo)

    @Query("DELETE FROM collect_videos WHERE videoId = :videoId")
    suspend fun deleteByVideoId(videoId: Int)

    @Query("SELECT * FROM collect_videos ORDER BY created_At DESC")
    fun getAllFavorites(): LiveData<List<CollectVideo>>

    @Query("SELECT * FROM collect_videos WHERE videoId = :videoId")
    suspend fun getFavoriteByVideoId(videoId: Int): CollectVideo?
}