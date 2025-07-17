package com.example.module_home.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.lib_network.RetrofitClient
import com.example.module_home.PagingSource.homerecommendPagingSource
import com.example.module_home.model.Recommend
import kotlinx.coroutines.flow.Flow

object homecommendRepository {

    private const val PAGE_SIZE = 10

    private val recommendApiservice = RetrofitClient.getService(com.example.module_home.apiService.recommendApiservice::class.java)

    fun getPagingData(): Flow<PagingData<Recommend>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { homerecommendPagingSource(recommendApiservice) }
        ).flow
    }

}