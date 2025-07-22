package com.example.module_video.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_network.RetrofitClient
import com.example.module_video.apiService.commentService
import com.example.module_video.apiService.relatedService
import com.example.module_video.model.Comment
import com.example.module_video.model.invokeitem
import kotlinx.coroutines.launch

class relatedViewModel : ViewModel() {

    val relatedApivice = RetrofitClient.getService(relatedService::class.java)

    //val commentApiservice = RetrofitClient.getService(commentService::class.java)

    private val _relatedList = MutableLiveData<List<invokeitem>>()
    val relatedList: LiveData<List<invokeitem>> = _relatedList

    private val _commentList = MutableLiveData<List<Comment>>()
    val commentList: LiveData<List<Comment>> = _commentList

    fun getdata(firstItem: invokeitem) {
        viewModelScope.launch {
            try {
                val response = relatedApivice.getRelatedData(firstItem.id)
                val validItem = response.itemList.filter { item ->
                    item.type == "videoSmallCard" && item.data != null
                }
                val mappedList = validItem.mapNotNull { item ->
                    item.data?.let { data ->
                        // 修复点1：安全处理tags字段
                        val tagName = data.tags?.firstOrNull()?.name ?: ""

                        invokeitem(
                            playuri = data.playUrl,
                            cover = data.cover?.feed ?: "",
                            id = data.id,
                            title = data.title,
                            author = data.author?.name ?: "未知作者",
                            authoricon = data.author?.icon ?: "",
                            // 修复点2：使用安全获取的tagName
                            tags = tagName,
                            des = data.description,
                            likecount = data.consumption?.realCollectionCount ?: 0,
                            collectcount = data.consumption?.collectionCount ?: 0,
                            isLike = data.collected,
                            isCollect = data.reallyCollected,
                            shareUrl = data.webUrl.raw
                        )
                    }
                }
                Log.d("fffffffff", "getdata: $mappedList")
                val maxList = listOf(firstItem) + mappedList
                _relatedList.value = maxList
            } catch (e: Exception) {
                // 修复点3：添加更有意义的错误日志
                Log.e("relatedViewModel", "获取相关数据失败: ${e.message}", e)
            }
        }
    }
    /*
    fun getcomment(firstItem:invokeitem){
        viewModelScope.launch {
            try{
                val response = commentApiservice.getCommentData(firstItem.id)
                val zList = response.itemList.filter { item->
                    item.type == "reply" && item.data != null
                }
                _commentList.value = zList
                Log.d("faffffffff", "getcomment: ${zList[1].data?.message}")
            }catch (e:Exception){
                e.printStackTrace()
                Log.d("fffffffff", "getcomment: $e")
            }
        }
    }
    */
}