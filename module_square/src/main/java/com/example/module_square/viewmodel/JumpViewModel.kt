package com.example.module_square.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.module_square.bean.ChildTabBean
import com.example.module_square.bean.TabListBean
import com.example.module_square.retrofit.Square
import com.example.module_square.pagingsource.JumpPagingSource

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class JumpViewModle : ViewModel() {
    private var _ChildTabStateFlow = MutableStateFlow<List<ChildTabBean>?>(null)
    val childTabStateFlow : StateFlow<List<ChildTabBean>?>
        get()= _ChildTabStateFlow

    fun getChildData(){
        viewModelScope.launch(Dispatchers.IO) {
            val dateList = Square.tabService.getCommunityTab().tabInfo.tabList.map { it.id.toString() }
            val childList = mutableListOf<ChildTabBean>()
            for (id in dateList){
                val response : ChildTabBean = if(id == "-1"){
                    Square.childTabService.getChildTab("0","","","")
                }else{
                    Square.childTabService.getChildTab(id,"","","")
                }
                childList.add(response)
            }
            _ChildTabStateFlow.emit(childList)
        }
    }
    fun getChildTabData(id :String)= Pager(PagingConfig(10,5)){JumpPagingSource(id)}
        .flow.cachedIn(viewModelScope)

}