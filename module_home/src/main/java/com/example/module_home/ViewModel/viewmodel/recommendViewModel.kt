package com.example.module_home.ViewModel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.module_home.model.Recommend
import com.example.module_home.repository.homecommendRepository
import kotlinx.coroutines.flow.Flow

class RecommendViewModel : ViewModel(){

    fun getPagingData(): Flow<PagingData<Recommend>> {
        return homecommendRepository.getPagingData().cachedIn(viewModelScope)
    }
}