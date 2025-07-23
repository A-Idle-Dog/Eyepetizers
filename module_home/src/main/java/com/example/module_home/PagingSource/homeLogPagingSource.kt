package com.example.module_home.PagingSource

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.module_home.apiService.logApiservice
import com.example.module_home.model.Daily

class homelogPagingSource(private val logApiservice: logApiservice) :
    PagingSource<String, Daily>() {
    override fun getRefreshKey(state: PagingState<String, Daily>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey
        }
    }
    override suspend fun load(params: LoadParams<String>): LoadResult<String, Daily> {
        return try {
            val currentKey = params.key
            val response = if (currentKey == null) {
                logApiservice.getFirst()
            } else {
                logApiservice.getNextPage(currentKey)
            }
            Log.d("Paging", "Loaded page $currentKey, items: ${response.itemList.size}")
            LoadResult.Page(
                data = response.itemList,
                prevKey = null,
                nextKey = response.nextPageUrl
            )
        }catch (e: Exception){
            Log.e("Paging", "Load error", e)
            LoadResult.Error(e)
        }
    }
}