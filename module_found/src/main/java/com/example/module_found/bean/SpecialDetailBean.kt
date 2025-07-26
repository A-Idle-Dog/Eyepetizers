package com.example.module_found.bean

import java.io.Serializable

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
data class SpecialDetailBean(
    val id: Int?,
    val headerImage: String?,
    val brief: String?,
    val text: String?,
    val shareLink: String?,
    val itemList: List<AutoPlayFollowCardItem>?,
    val trackingData: Any?,
    val tag: Any?,
): Serializable
data class AutoPlayFollowCardItem(
    val type: String?,
    val data: FollowCardData?,
    val trackingData: Any?,
    val tag: Any?,
    val id: Int?,
    val adIndex: Int?
): Serializable
data class FollowCardData(
    val dataType: String?,
    val header: SpHeader?,
    val content: Content?,
    val adTrack: List<AdTrackItem>?,
    val trackingData: Any?,
    val tag: Any?,
    val id: Int?,
    val adIndex: Int?
): Serializable
data class SpHeader(
    val id: Int?,
    val actionUrl: String?,
    val labelList: Any?,
    val icon: String?,
    val iconType: String?,
    val time: Long?,
    val showHateVideo: Boolean?,
    val followType: String?,
    val tagId: Int?,
    val tagName: Any?,
    val issuerName: String?,
    val topShow: Boolean?
): Serializable
data class Content(
    val type: String?,
    val data: SpVideoBeanForClient?,
    val trackingData: Any?,
    val tag: Any?,
    val id: Int?,
    val adIndex: Int?
): Serializable
data class SpVideoBeanForClient(
    val dataType: String?,
    val id: Int?,
    val title: String?,
    val description: String?,
    val library: String?,
    val tags: List<SpTag>?,
    val consumption: SpConsumption?,
    val resourceType: String?,
    val slogan: String?,
    val provider: SpProvider?,
    val category: String?,
    val author: SpAuthor?,
    val cover: SpCover?,
    val playUrl: String?,
    val thumbPlayUrl: String?,
    val duration: Int?,
    val webUrl: SpWebUrl?,
    val releaseTime: Long?,
    val playInfo: List<SpPlayInfo>?,
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
    val videoPosterBean: SpVideoPosterBean?,
    val idx: Int?,
    val shareAdTrack: Any?,
    val favoriteAdTrack: Any?,
    val webAdTrack: Any?,
    val date: Long?,
    val promotion: Promotion?,
    val label: Label?,
    val labelList: List<LabelListItem>?,
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
): Serializable
data class SpTag(
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
): Serializable
data class SpConsumption(
    val collectionCount: Int?,
    val shareCount: Int?,
    val replyCount: Int?,
    val realCollectionCount: Int?
): Serializable
data class SpProvider(
    val name: String?,
    val alias: String?,
    val icon: String?
): Serializable
data class SpAuthor(
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
): Serializable
data class Follow(
    val itemType: String?,
    val itemId: Int?,
    val followed: Boolean?
): Serializable
data class Shield(
    val itemType: String?,
    val itemId: Int?,
    val shielded: Boolean?
): Serializable
data class SpCover(
    val feed: String?,
    val detail: String?,
    val blurred: String?,
    val sharing: Any?,
    val homepage: Any?
): Serializable
data class SpWebUrl(
    val raw: String?,
    val forWeibo: String?
): Serializable
data class SpPlayInfo(
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

data class SpVideoPosterBean(
    val scale: Double?,
    val url: String?,
    val fileSizeStr: String?
)


data class Promotion(
    val text: String?
)


data class Label(
    val text: String?,
    val card: String?,
    val detail: String?
)


data class LabelListItem(
    val text: String?,
    val actionUrl: String?
)
