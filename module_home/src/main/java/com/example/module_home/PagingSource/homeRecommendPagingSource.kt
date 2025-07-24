package com.example.module_home.PagingSource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.module_home.apiService.recommendApiservice
import com.example.module_home.model.Recommend

class homerecommendPagingSource(private val recommendApiservice: recommendApiservice) :
    PagingSource<String, Recommend>() {
    override fun getRefreshKey(state: PagingState<String, Recommend>): String? {
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Recommend> {
        return try {
            val page = params.key
            val response = if(page==null){
                recommendApiservice.getrecommendDate()
            }else{
                recommendApiservice.getRecommendDate(page)
            }
            val filteredList = response.itemList.filter { item ->
                item.type != "squareCardCollection"
            }
            Log.d("PagingSource", "Response received: $response")
            val itemList = filteredList ?: emptyList()
            Log.d("PagingSource", "Loaded ${itemList.size} items for page $page")
            LoadResult.Page(
                data = itemList,
                prevKey = null,
                nextKey = response.nextPageUrl
            )
        }catch (e: Exception){
            Log.e("PagingSource", "Error loading page: ${params.key}", e)
            LoadResult.Error(e)
        }
    }
}