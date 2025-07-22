package com.example.module_square.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.module_square.bean.Rec
import com.example.module_square.retrofit.Square

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class SquarePagingSource():PagingSource<Int,Rec>() {
    private  var nextUrl =""
    override fun getRefreshKey(state: PagingState<Int, Rec>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Rec> {
       return try {
           val now = params.key?:1
           val squareDate=if (nextUrl==""){
               Square.squareService.getSquareMore("","")
           }else{
               Square.squareService.getSquareMore( nextUrl.split("&")[0].split("=")[1],
                   nextUrl.split("&")[1].split("=")[1])
           }
           Log.d("APIResponse", "原始数据：${squareDate.itemList.map { it.type }}")
           nextUrl=squareDate.nextPageUrl.substring(
               53,
               squareDate.nextPageUrl.length
           )
           val realData = mutableListOf<Rec>()
           for (data in squareDate.itemList) {
               if (data.type == "communityColumnsCard"&&data.data.content.type=="ugcPicture") {
                   data.data.content.data.run {
                       realData.add(
                           Rec(
                               cover.feed,
                               description,
                               owner.avatar,
                               owner.nickname,
                               if (resourceType == "ugc_picture") urls else null,
                               resourceType,
                               if (resourceType == "ugc_video") playUrl else null,
                               consumption.collectionCount,
                               consumption.realCollectionCount,
                               collected,
                               tags?: emptyList(),
                               id
                           )
                       )
                   }
               }
           }
           val preKey = if (now>1) now-1 else null
           val nextKey = if (nextUrl.isNotEmpty()) now+1 else null
           LoadResult.Page(data=realData,preKey,nextKey)
       }catch (e:Exception){
           Log.e("APIError", "请求失败：${e.message}", e)
           LoadResult.Error(throwable = e)
       }
    }
}