package com.example.module_home.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.lib_network.RetrofitClient
import com.example.module_home.PagingSource.homelogPagingSource
import com.example.module_home.model.Daily
import kotlinx.coroutines.flow.Flow

object homelogRepository {

    private const val PAGE_SIZE = 10

        private val logApiservice = RetrofitClient.getService(com.example.module_home.apiService.logApiservice::class.java)

    fun getPagingData(): Flow<PagingData<Daily>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { homelogPagingSource(logApiservice) }
        ).flow
    }

}