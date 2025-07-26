package com.example.module_found.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.module_found.bean.CategoryBean
import com.example.module_found.paging.CateDetailPaging
import com.example.module_found.retrofit.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class CotegoryViewModel :ViewModel() {
    private var _CotegoryStateFlow = MutableStateFlow<List<CategoryBean>?>(null)
    val categoryStateFlow :StateFlow<List<CategoryBean>?>
        get() = _CotegoryStateFlow
    private val _isConnected = MutableStateFlow<Boolean?>(null)
    val isConnected: StateFlow<Boolean?> = _isConnected


    fun getCategoryData(){
        viewModelScope.launch (Dispatchers.IO){
            try {
                val response = Category.category.getCategory()
                _CotegoryStateFlow.emit(response)
                _isConnected.emit(true)
            }catch (e:Exception){
                e.printStackTrace()
                if (e is IOException) { // 网络错误
                    _isConnected.emit(false)
                } else {
                    _isConnected.emit(null) // 非网络错误
                }
            }

        }
    }
    fun getCateDetail(id :String)=Pager(PagingConfig(20,10)){CateDetailPaging(id)}.flow.cachedIn(viewModelScope)
}
