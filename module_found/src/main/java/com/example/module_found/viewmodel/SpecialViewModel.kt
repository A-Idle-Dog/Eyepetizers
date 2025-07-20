package com.example.module_found.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.module_found.bean.Banner2Item
import com.example.module_found.bean.SpecialDetailBean
import com.example.module_found.paging.AllPaging
import com.example.module_found.retrofit.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class SpecialViewModel:ViewModel() {
    private var _SpecialStateFlow= MutableStateFlow<List<SpecialDetailBean>?>(null)
    val specialStateFlow:StateFlow<List<SpecialDetailBean>?>
        get()=_SpecialStateFlow
    private var _SpecialDetialStateFlow = MutableStateFlow<SpecialDetailBean?>(null)
    val specialDetialStateFlow: StateFlow<SpecialDetailBean?>
        get() = _SpecialDetialStateFlow.asStateFlow()

    fun getSpecial(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Category.special.getSpecial()
                val responseId = response.itemList?.map {
                    it.data?.id.toString()
                }
                val response1 = mutableListOf<SpecialDetailBean>()
                for (id in responseId!!){
                    val responseItem = Category.special.getSpecialDetail(id)
                    response1.add(responseItem)
                }
                _SpecialStateFlow.emit(response1)
            }catch (e:Exception){
                e.printStackTrace()
            }

        }
    }
    fun getSpecialMore()=Pager(PagingConfig(20,10)){AllPaging()}.flow.cachedIn(viewModelScope)
    fun getSpecialData(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val responseItem = Category.special.getSpecialDetail(id)

                _SpecialDetialStateFlow.emit(responseItem)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}