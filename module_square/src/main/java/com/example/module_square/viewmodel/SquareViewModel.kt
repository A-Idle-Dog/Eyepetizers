package com.example.module_square.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.module_square.bean.TabListBean
import com.example.lib.NetStatus
import com.example.module_square.bean.SquareBean
import com.example.module_square.pagingsource.SquarePagingSource
import com.example.module_square.retrofit.Square
/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class SquareViewModule : ViewModel() {
    private var _TabStateFlow = MutableStateFlow<SquareBean?>(null)
    val tabStateFlow : StateFlow<SquareBean?>
        get() = _TabStateFlow.asStateFlow()
    private  var _NetState = MutableLiveData<NetStatus>()
    val netState: LiveData<NetStatus>
        get() = _NetState


    /*fun getTabData()  {
        _NetState.value=NetStatus.LOADING

            viewModelScope.launch (Dispatchers.IO){
                try {
                val response = Square.tabService.getCommunityTab()
                _TabStateFlow.emit(response)
                _NetState.postValue(NetStatus.SUCCESS)
                }catch (e:Exception){
                    e.printStackTrace()
                    _NetState.postValue(NetStatus.FAIL)
            }

        }
    }*/
    fun getSquare()=Pager(PagingConfig(10,5)){SquarePagingSource()}.flow.cachedIn(viewModelScope)
    }
