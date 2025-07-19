package com.example.module_found.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.module_found.bean.SpecialDetailBean
import com.example.module_found.retrofit.Category

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
class AllPaging():PagingSource<Int,SpecialDetailBean>() {
    private var nextUrl :String?= ""
    override fun getRefreshKey(state: PagingState<Int, SpecialDetailBean>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SpecialDetailBean> {
        return try {
            val now = params.key?:1
            val SpeData =if (nextUrl==""){
                Category.special.getSpecial()


            }else{
                Category.special.getMoreSpecial(
                    nextUrl!!.split("&")[0].split("=")[1],
                    nextUrl!!.split("&")[1].split("=")[1]
                )

            }
            nextUrl=SpeData.nextPageUrl
            val prevKey = if (now > 1) now - 1 else null
            val nextKey = if (nextUrl != "") now + 1 else null
            val responseId = SpeData.itemList?.map {
                it.data?.id.toString()
            }
            var response1 = mutableListOf<SpecialDetailBean>()
            for (id in responseId!!){
                val responseItem = Category.special.getSpecialDetail(id)
                response1.add(responseItem)
            }
            LoadResult.Page(
                data = response1,
                prevKey,
                nextKey
            )

        }catch (e:Exception){ LoadResult.Error(throwable = e)}

    }
}