package com.example.module_video.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.data.Dao.CollectVideoDao
import com.example.module_video.model.invokeitem

class CollectViewModel(dao: CollectVideoDao) : ViewModel() {
    val _rawCollectList  = dao.getAllFavorites()

    val collectList :LiveData<List<invokeitem>> = _rawCollectList.switchMap { list->
        val i = list.map{item->
           invokeitem(
               playuri = item.palyurl,
               cover = item.cover,
               id = item.videoId,
               title = item.title,
               author = item.author,
               authoricon = item.authoricon,
               tags = item.tags,
               des = item.des,
               likecount = item.likecount,
               collectcount = item.collectcount,
               isLike = item.isLike,
               isCollect = item.isCollect,
               shareUrl = item.shareUrl,
               source = item.source,
               currentPosition = item.currentPosition
           )
        }
        MutableLiveData(i)
    }
}