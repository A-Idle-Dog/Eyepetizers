package com.example.module_found.bean

/**

 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
data class SpecialBean(
    var itemList: List<Banner2Item>?,
    val count: Int?,
    val total: Int?,
    val nextPageUrl: String?,
    val adExist: Boolean?
)
data class Banner2Item(
    val type: String?,
    val data: BannerData?,
    val trackingData: Any?, // JSON中为null，用Any?接收
    val tag: Any?, // JSON中为null，用Any?接收
    val id: Int?,
    val adIndex: Int?
)
data class BannerData(
    val dataType: String?,
    val id: Int?,
    val title: String?,
    val description: String?,
    val image: String?,
    val actionUrl: String?,
    val adTrack: List<Any>?, // 空数组，用List<Any>?接收
    val shade: Boolean?,
    val label: BannerLabel?,
    val labelList: List<Any>?, // 空数组，用List<Any>?接收
    val header: Any?, // JSON中为null，用Any?接收
    val autoPlay: Boolean?
)

/**
 * BannerData中label字段的数据类
 */
data class BannerLabel(
    val text: String?,
    val card: String?,
    val detail: Any? // JSON中为null，用Any?接收
)
