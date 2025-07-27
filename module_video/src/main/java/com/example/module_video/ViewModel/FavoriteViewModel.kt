package com.example.module_video.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.data.Dao.FavoriteVideoDao
import com.example.module_video.model.invokeitem

class FavoriteViewModel(private val dao: FavoriteVideoDao) : ViewModel() {

    private val _rawFavorites = dao.getAllFavorites()

    val favoriteVideos: LiveData<List<invokeitem>> = _rawFavorites.switchMap { list ->
        val converted = list.map { video ->
            invokeitem(
                playuri = video.palyurl,
                cover = video.cover,
                id = video.videoId,
                title = video.title,
                author = video.author,
                authoricon = video.authoricon,
                tags = video.tags,
                des = video.des,
                likecount = video.likecount,
                collectcount = video.collectcount,
                isLike = video.isLike,
                isCollect = video.isCollect,
                shareUrl = video.shareUrl,
                source = video.source,
                currentPosition = video.currentPosition
            )
        }
        MutableLiveData(converted)
    }
}