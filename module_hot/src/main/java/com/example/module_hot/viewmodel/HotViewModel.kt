package com.example.module_hot.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module_hot.bean.HotBean
import com.example.module_hot.bean.VideoItem
import com.example.module_hot.retrofit.HotNet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class HotViewModel:ViewModel() {
    private var _HotStateFlow = MutableStateFlow<HotBean?>(null)
    val hotStateFlow :StateFlow<HotBean?>
        get()=_HotStateFlow
    fun getHot(strategy:String){

            viewModelScope.launch(Dispatchers.IO) {
                try {
                val response =HotNet.hot.getHot(strategy)
                _HotStateFlow.emit(response)
            }catch (e:Exception){
                    e.printStackTrace()
        }
        }
    }
}