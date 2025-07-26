package com.example.module_found.bean

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
data class CategoryDetailBean(
    val itemList: List<Item>,
    val count: Int,
    val total: Int,
    val nextPageUrl: String?,
    val adExist: Boolean
)
data class Item(
    val type: String,
    val id: Long,
    val data: Data,
    val adIndex: Int
)
data class Data(
    val dataType: String,
    val header: Header,
    val content: VideoContent
)
data class Header(
    val id: Long,
    val title: String?,
    val actionUrl: String?,
    val icon: String?,
    val iconType: String?,
    val description: String?,
    val time: Long?,
    val showHateVideo: Boolean
)
data class VideoContent(
    val type: String,
    val id: Int,
    val adIndex: Int,
    val data: VideoBeanForClient
)
data class VideoBeanForClient(
    val dataType: String,
    val id: Long,
    val title: String?,
    val description: String?,
    val library: String?,
    val tags: List<Tag>?,
    val consumption: Consumption?,
    val resourceType: String?,
    val slogan: String?,
    val provider: Provider?,
    val category: String?,
    val author: Author?,
    val cover: Cover?,
    val playUrl: String?,
    val thumbPlayUrl: Any?,
    val duration: Int?,
    val webUrl: WebUrl?,
    val releaseTime: Long?,
    val playInfo: List<PlayInfo>?,
    val campaign: String?,
    val waterMarks: String?,
    val ad: Boolean,
    val adTrack: List<String>?,
    val type: String?,
    val titlePgc: String?,
    val descriptionPgc: String?,
    val remark: String?,
    val ifLimitVideo: Boolean,
    val searchWeight: Int?,
    val brandWebsiteInfo: String?,
    val videoPosterBean: VideoPosterBean?,
    val idx: Int?,
    val shareAdTrack: String?,
    val favoriteAdTrack: String?,
    val webAdTrack: String?,
    val date: Long?,
    val promotion: String?,
    val label: String?,
    val labelList: List<String>?,
    val descriptionEditor: String?,
    val collected: Boolean,
    val reallyCollected: Boolean,
    val played: Boolean,
    val subtitles: List<String>?,
    val lastViewTime: Long?,
    val playlists: String?,
    val src: String?,
    val recallSource: String?,
    val recall_source: String?,
    val candidateId: Int?,
    val sourceUrl: String?,
    val tailTimePoint: Int?,// 尾部时间点
    val createTime: Long?,
    val updateTime: Long?,
    val infoStatus: String?,
    val status: String?,
    val showLabel: Boolean,
    val premiereDate: Long?,
    val areaSet: List<String>?,
    val subtitleStatus: String?,
    val translateStatus: String?
)
data class Tag(
    val id: Int,
    val name: String?,
    val actionUrl: String?,
    val desc: String?,
    val bgPicture: String?,
    val headerImage: String?,
    val tagRecType: String?,
    val childTagList: List<Tag>?,
    val childTagIdList: List<Int>?,
    val haveReward: Boolean,
    val ifNewest: Boolean,
    val newestEndTime: Long?,
    val communityIndex: Int?,
    val alias: String?,
    val type: String?,
    val tagStatus: String?,
    val level: Int?,
    val top: Int?,
    val parentId: Int?,
    val recWeight: Double?,
    val ifShow: Boolean,
    val relationType: Int?
)


data class Consumption(
    val collectionCount: Int?,
    val shareCount: Int?,
    val replyCount: Int?,
    val realCollectionCount: Int?,
    val playCount: Int?
)


data class Provider(
    val name: String?,
    val alias: String?,
    val icon: String?,
    val id: Int?,
    val status: String?
)


data class Author(
    val id: Long?,
    val icon: String?,
    val name: String?,
    val description: String?,
    val link: String?,
    val latestReleaseTime: Long?,
    val videoNum: Int?,
    val adTrack: String?,
    val follow: FollowStatus?,
    val shield: ShieldStatus?,
    val approvedNotReadyVideoCount: Int?,
    val ifPgc: Boolean?,
    val recSort: Int?,
    val expert: Boolean?,
    val cover: String?,
    val authorType: String?,
    val authorStatus: String?,
    val library: String?,
    val pendingVideoCount: Int?,
    val amplifiedLevelId: Int?
)


data class FollowStatus(
    val itemType: String?,
    val itemId: Long?,
    val followed: Boolean?
)


data class ShieldStatus(
    val itemType: String?,
    val itemId: Long?,
    val shielded: Boolean?
)


data class Cover(
    val feed: String?,
    val detail: String?,
    val blurred: String?,
    val sharing: String?,
    val homepage: String?
)


data class WebUrl(
    val raw: String?,
    val forWeibo: String?
)


data class PlayInfo(
    val height: Int?,
    val width: Int?,
    val urlList: List<UrlInfo>?,
    val id: Long?,
    val videoId: Long?,
    val name: String?,
    val type: String?,
    val url: String?,
    val duration: Int?,
    val bitRate: Int?,
    val dimension: String?,
    val size: Int?
)


data class UrlInfo(
    val name: String?,
    val url: String?,
    val size: Int?
)


data class VideoPosterBean(
    val scale: Double?,
    val url: String?,
    val fileSizeStr: String?
)
