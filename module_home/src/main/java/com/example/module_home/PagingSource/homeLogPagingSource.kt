package com.example.module_home.PagingSource

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.module_home.apiService.logApiservice
import com.example.module_home.model.Daily

class homelogPagingSource(private val logApiservice: logApiservice) :
    PagingSource<Int, Daily>() {
    override fun getRefreshKey(state: PagingState<Int, Daily>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Daily> {
        return try {
            val pageUrl = params.key
            val response = logApiservice.getlogDate(pageUrl)
            Log.d("Paging", "Loaded page $pageUrl, items: ${response.itemList.size}")
            LoadResult.Page(
                data = response.itemList,
                prevKey = null,
                nextKey = if (response.itemList.isEmpty()) null else pageUrl
            )
        }catch (e: Exception){
            Log.e("Paging", "Load error", e)
            LoadResult.Error(e)
        }
    }
}