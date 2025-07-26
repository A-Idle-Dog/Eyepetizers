package com.example.module_hot.bean

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
data class HotBean(
    val itemList: List<VideoItem>?,
    val count: Int?,
    val total: Int?,
    val nextPageUrl: String?,
    val adExist: Boolean?
)
data class VideoItem(
    val type: String?,
    val data: VideoBeanForClient?,
    val trackingData: Any?,
    val tag: Any?,
    val id: Int?,
    val adIndex: Int?
)
data class VideoBeanForClient(
    val dataType: String?,
    val id: Int?,
    val title: String?,
    val description: String?,
    val library: String?,
    val tags: List<Tag>?,
    val consumption: VideoConsumption?,
    val resourceType: String?,
    val slogan: String?,
    val provider: Provider?,
    val category: String?,
    val author: Author?,
    val cover: Cover?,
    val playUrl: String?,
    val thumbPlayUrl: String?,
    val duration: Int?,
    val webUrl: WebUrl?,
    val releaseTime: Long?,
    val playInfo: List<PlayInfo>?,
    val campaign: Any?,
    val waterMarks: Any?,
    val ad: Boolean?,
    val adTrack: List<AdTrackItem>?,
    val type: String?,
    val titlePgc: String?,
    val descriptionPgc: String?,
    val remark: String?,
    val ifLimitVideo: Boolean?,
    val searchWeight: Int?,
    val brandWebsiteInfo: Any?,
    val videoPosterBean: Any?,
    val idx: Int?,
    val shareAdTrack: Any?,
    val favoriteAdTrack: Any?,
    val webAdTrack: Any?,
    val date: Long?,
    val promotion: Any?,
    val label: Any?,
    val labelList: List<Any>?,
    val descriptionEditor: String?,
    val collected: Boolean?,
    val reallyCollected: Boolean?,
    val played: Boolean?,
    val subtitles: List<Any>?,
    val lastViewTime: Any?,
    val playlists: Any?,
    val src: Any?,
    val recallSource: Any?,
    val recall_source: Any?
)
data class Tag(
    val id: Int?,
    val name: String?,
    val actionUrl: String?,
    val adTrack: Any?,
    val desc: String?,
    val bgPicture: String?,
    val headerImage: String?,
    val tagRecType: String?,
    val childTagList: Any?,
    val childTagIdList: Any?,
    val haveReward: Boolean?,
    val ifNewest: Boolean?,
    val newestEndTime: Any?,
    val communityIndex: Int?
)
data class VideoConsumption(
    val collectionCount: Int?,
    val shareCount: Int?,
    val replyCount: Int?,
    val realCollectionCount: Int?
)
data class Provider(
    val name: String?,
    val alias: String?,
    val icon: String?
)
data class Author(
    val id: Int?,
    val icon: String?,
    val name: String?,
    val description: String?,
    val link: String?,
    val latestReleaseTime: Long?,
    val videoNum: Int?,
    val adTrack: Any?,
    val follow: Follow?,
    val shield: Shield?,
    val approvedNotReadyVideoCount: Int?,
    val ifPgc: Boolean?,
    val recSort: Int?,
    val expert: Boolean?
)
data class Follow(
    val itemType: String?,
    val itemId: Int?,
    val followed: Boolean?
)
data class Shield(
    val itemType: String?,
    val itemId: Int?,
    val shielded: Boolean?
)
data class Cover(
    val feed: String?,
    val detail: String?,
    val blurred: String?,
    val sharing: Any?,
    val homepage: String?
)
data class WebUrl(
    val raw: String?,
    val forWeibo: String?
)
data class PlayInfo(
    val height: Int?,
    val width: Int?,
    val urlList: List<UrlListItem>?,
    val name: String?,
    val type: String?,
    val url: String?
)
data class UrlListItem(
    val name: String?,
    val url: String?,
    val size: Int?
)
data class AdTrackItem(

    val id: Int?,
    val organization: String?,
    val viewUrl: String?,
    val clickUrl: String?,
    val playUrl: String?,
    val monitorPositions: Any?,
    val needExtraParams: Any?
)
