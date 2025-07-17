package com.example.module_home.PagingSource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.module_home.apiService.recommendApiservice
import com.example.module_home.model.Recommend

class homerecommendPagingSource(private val recommendApiservice: recommendApiservice) :
    PagingSource<Int, Recommend>() {
    override fun getRefreshKey(state: PagingState<Int, Recommend>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recommend> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            Log.d("PagingSource", "Loading page $page, pageSize $pageSize")
            val response = recommendApiservice.getrecommendDate(page, pageSize)
            Log.d("PagingSource", "Response received: $response")
            val itemList = response.itemList ?: emptyList()
            Log.d("PagingSource", "Loaded ${itemList.size} items for page $page")
            LoadResult.Page(
                data = itemList,
                prevKey = null,
                nextKey = if (itemList.isEmpty()) null else page + 1
            )
        }catch (e: Exception){
            Log.e("PagingSource", "Error loading page: ${params.key}", e)
            LoadResult.Error(e)
        }
    }
}