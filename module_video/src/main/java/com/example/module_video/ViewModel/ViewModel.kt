package com.example.module_video.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_network.RetrofitClient
import com.example.module_video.apiService.relatedService
import com.example.module_video.model.Item
import com.example.module_video.model.OthersData
import com.example.module_video.model.invokeitem
import kotlinx.coroutines.launch

class relatedViewModel : ViewModel() {

    val relatedApivice = RetrofitClient.getService(relatedService::class.java)

    private val _relatedList = MutableLiveData<List<invokeitem>>() // 改为自定义类型
    val relatedList: LiveData<List<invokeitem>> = _relatedList

    fun getdata(firstItem : invokeitem){
        viewModelScope.launch {
            try{
                val response = relatedApivice.getRelatedData(firstItem.id)
                val validItem = response.itemList.filter{item->
                    item.type == "videoSmallCard" && item.data != null
                }
                val mappedList = validItem.mapNotNull { item ->
                    item.data?.let { data->
                        val tagNames = data.tags[0]

                        invokeitem(
                            playuri = data.playUrl,
                            cover = data.cover?.feed ?: "",
                            id = data.id,
                            title = data.title,
                            author = data.author?.name ?: "未知作者",
                            authoricon = data.author?.icon ?: "",
                            tags = tagNames.toString(),
                            des = data.description,
                            likecount = data.consumption?.realCollectionCount ?: 0,
                            collectcount = data.consumption?.collectionCount ?: 0
                        )
                    }
                }
                val maxList = listOf(firstItem)+mappedList
                _relatedList.value = maxList
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}