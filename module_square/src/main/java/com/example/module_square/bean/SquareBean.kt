package com.example.module_square.bean

import java.io.Serializable

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
data class SquareBean(
    val adExist: Boolean,
    val count: Int,
    val itemList: List<SquareItem>,//1
    val nextPageUrl: String,
    val total: Int
): Serializable

data class SquareItem(
    val adIndex: Int,
    val `data`: SquareData,//2
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String
): Serializable

data class SquareData(
    val adTrack: Any,
    val content: SquareContent,//3
    val count: Int,
    val dataType: String,
    val footer: Any,
): Serializable

data class SquareContent(
    val adIndex: Int,
    val `data`: SquareDataX,
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String
): Serializable


data class SquareDataX(
    val addWatermark: Boolean,
    val area: String,
    val checkStatus: String,
    val city: String,
    val collected: Boolean,
    val consumption: SquareConsumption,
    val cover: SquareCover,
    val createTime: Long,
    val dataType: String,
    val description: String,
    val duration: Int,
    val height: Int,
    val id: Int,
    val ifMock: Boolean,
    val latitude: Double,
    val library: String,
    val longitude: Double,
    val owner: SquareOwner,
    val playUrl: String,
    val playUrlWatermark: String,
    val privateMessageActionUrl: Any,
    val reallyCollected: Boolean,
    val recentOnceReply: SquareRecentOnceReply,
    val releaseTime: Long,
    val resourceType: String,
    val selectedTime: Any,
    val source: String,
    val tags: List<SquareTag>,
    val title: String,
    val transId: Any,
    val type: String,
    val uid: Int,
    val updateTime: Long,
    val url: String,
    val urls: List<String>,
    val urlsWithWatermark: List<String>,
    val validateResult: String,
    val validateStatus: String,
    val validateTaskId: String,
    val width: Int
): Serializable

data class SquareConsumption(
    val collectionCount: Int,
    val realCollectionCount: Int,
    val replyCount: Int,
    val shareCount: Int
): Serializable

data class SquareCover(
    val blurred: Any,
    val detail: String,
    val feed: String,
    val homepage: Any,
    val sharing: Any
): Serializable

data class SquareOwner(
    val actionUrl: String,
    val area: Any,
    val avatar: String,
    val birthday: Long,
    val city: String,
    val country: String,
    val cover: String,
    val description: String,
    val expert: Boolean,
    val followed: Boolean,
    val gender: String,
    val ifPgc: Boolean,
    val job: String,
    val library: String,
    val limitVideoOpen: Boolean,
    val nickname: String,
    val registDate: Long,
    val releaseDate: Long,
    val uid: Int,
    val university: String,
    val userType: String
): Serializable

data class SquareRecentOnceReply(
    val actionUrl: String,
    val contentType: Any,
    val dataType: String,
    val message: String,
    val nickname: String
): Serializable

data class SquareTag(
    val actionUrl: String,
    val adTrack: Any,
    val bgPicture: String,
    val childTagIdList: Any,
    val childTagList: Any,
    val communityIndex: Int,
    val desc: String,
    val haveReward: Boolean,
    val headerImage: String,
    val id: Int,
    val ifNewest: Boolean,
    val name: String,
    val newestEndTime: Long,
    val tagRecType: String
): Serializable
data class Squarepic(
    val coverUrl:String,
    val title:String,
    val icon:String,
    val author:String,
    val picUrls:List<String>?,
    val type: String,
    val palyUrl:String?,
    val clloect:Int,
    val realCollect :Int,
    val liked:Boolean,
    val tags: List<SquareTag>,
    val uid: Int,
    val ip:String,
    val time: Long,
    val picHight :Int,
    val picWidth :Int
): Serializable
