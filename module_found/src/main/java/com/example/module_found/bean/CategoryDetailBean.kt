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
    val title: String?,// 标题（如"全球动画精选"）
    val actionUrl: String?,
    val icon: String?,// 图标URL
    val iconType: String?,// 图标类型（如"round"、"square"）
    val description: String?,// 描述（如"#动画 / 收录于 每日编辑精选"）
    val time: Long?,// 时间戳（毫秒，可能为null）
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
    val title: String?, // 视频标题
    val description: String?, // 视频描述
    val library: String?, // 所属库（如"DAILY"、"DEFAULT"）
    val tags: List<Tag>?, // 视频标签列表
    val consumption: Consumption?, // 消费数据（播放量、收藏量等）
    val resourceType: String?, // 资源类型（如"video"）
    val slogan: String?,
    val provider: Provider?, // 内容提供方
    val category: String?, // 分类（如"动画"、"科技"）
    val author: Author?, // 作者信息（可能为null）
    val cover: Cover?, // 封面图信息
    val playUrl: String?, // 播放地址
    val thumbPlayUrl: Any?, // 缩略图播放地址（未使用，为null）
    val duration: Int?, // 时长（秒）
    val webUrl: WebUrl?, // 网页链接
    val releaseTime: Long?, // 发布时间戳（毫秒）
    val playInfo: List<PlayInfo>?, // 播放信息列表（不同清晰度）
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
