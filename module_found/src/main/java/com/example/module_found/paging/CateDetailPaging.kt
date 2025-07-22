package com.example.module_found.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.module_found.bean.Item
import com.example.module_found.retrofit.Category


class CateDetailPaging (private val id :String?) :PagingSource<Int,Item>() {
    private var nextUrl :String?= ""
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
       return try {
           val now = params.key?:1
           val CateData = if (nextUrl==""){
               Category.category.getCategory(id,"","","")
           }else{
               Category.category.getCategory(
                   id,
                   nextUrl!!.split("&")[0].split("=")[1],
                   nextUrl!!.split("&")[1].split("=")[1],
                   nextUrl!!.split("&")[2].split("=")[1]
               )
           }
           nextUrl=CateData.nextPageUrl
           val prevKey = if (now > 1) now - 1 else null
           val nextKey = if (nextUrl != "") now + 1 else null
           LoadResult.Page(
               data = CateData.itemList,
               prevKey,
               nextKey
           )
       }catch (e :Exception){
           LoadResult.Error(throwable = e)
       }

    }
}