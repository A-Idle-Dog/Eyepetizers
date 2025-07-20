package com.example.module_video.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_network.RetrofitClient
import com.example.module_video.apiService.commentService
import com.example.module_video.model.Comment
import com.example.module_video.model.CommentResponse
import com.example.module_video.model.invokeitem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class commentViewModel : ViewModel() {

    val commentApiservice = RetrofitClient.getService(commentService::class.java)

    private val _commentList = MutableLiveData<List<Comment>>()
    val commentList: LiveData<List<Comment>> = _commentList

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
}