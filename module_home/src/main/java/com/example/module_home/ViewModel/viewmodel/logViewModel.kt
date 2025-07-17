package com.example.module_home.ViewModel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.module_home.model.Daily
import com.example.module_home.repository.homelogRepository
import kotlinx.coroutines.flow.Flow

class logViewModel: ViewModel() {

    fun getPagingData(): Flow<PagingData<Daily>> {
        return homelogRepository.getPagingData().cachedIn(viewModelScope)
    }

}