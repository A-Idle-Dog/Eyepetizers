package com.example.module_square.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.module_square.bean.Item
import com.example.module_square.retrofit.Square

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class JumpPagingSource(private val id :String) : PagingSource<Int, Item>() {
    private  var nextUrl =""
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val now = params.key?:1
            val childData =if (nextUrl==""){
                if (id=="0"){
                    Square.childTabService.getChildTab(id,"true","","")
                }else{
                    Square.childTabService.getChildTab(id,"","","")}
            }   else{
                val start= nextUrl.substringAfter("start=","")
                    .substringBefore("&")
                val num=nextUrl.substringAfter("num=","")
                Square.childTabService.getChildTab(id,"",start,num)
            }
            nextUrl=childData.nextPageUrl?:""
            val preKey = if (now>1) now-1 else null
            val nextKey = if (now<2) null else now+1
            LoadResult.Page(data=childData.itemList,preKey,nextKey)

        }catch (e:Exception){
            LoadResult.Error(throwable = e)
        }
    }
}