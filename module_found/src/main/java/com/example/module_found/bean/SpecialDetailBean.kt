package com.example.module_found.bean

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
)
data class AutoPlayFollowCardItem(
    val type: String?,
    val data: FollowCardData?,
    val trackingData: Any?, // JSON中为null
    val tag: Any?, // JSON中为null
    val id: Int?,
    val adIndex: Int?
)
data class FollowCardData(
    val dataType: String?, // 固定为"FollowCard"
    val header: SpHeader?, // 头部信息
    val content: Content?, // 内容信息
    val adTrack: List<AdTrackItem>?, // 广告跟踪数据
    val trackingData: Any?, // JSON中为null
    val tag: Any?, // JSON中为null
    val id: Int?,
    val adIndex: Int?
)
data class SpHeader(
    val id: Int?,
    val actionUrl: String?, // 跳转链接
    val labelList: Any?, // JSON中为null
    val icon: String?, // 图标URL
    val iconType: String?, // 图标类型（如"round"）
    val time: Long?, // 时间戳
    val showHateVideo: Boolean?,
    val followType: String?, // 关注类型（如"author"）
    val tagId: Int?,
    val tagName: Any?, // JSON中为null
    val issuerName: String?, // 发布者名称
    val topShow: Boolean?
)
data class Content(
    val type: String?, // 固定为"video"
    val data: SpVideoBeanForClient?, // 视频核心数据
    val trackingData: Any?, // JSON中为null
    val tag: Any?, // JSON中为null
    val id: Int?,
    val adIndex: Int?
)
data class SpVideoBeanForClient(
    val dataType: String?, // 固定为"VideoBeanForClient"
    val id: Int?,
    val title: String?, // 视频标题
    val description: String?, // 视频描述
    val library: String?, // 资源库标识（如"BLOCK"）
    val tags: List<SpTag>?, // 标签列表
    val consumption: SpConsumption?, // 消费数据（收藏/分享等）
    val resourceType: String?, // 资源类型（如"video"）
    val slogan: String?, // 口号（可为null）
    val provider: SpProvider?, // 视频提供商
    val category: String?, // 分类（如"时尚"）
    val author: SpAuthor?, // 作者信息
    val cover: SpCover?, // 封面图信息
    val playUrl: String?, // 播放地址
    val thumbPlayUrl: String?, // 缩略图播放地址（可为null）
    val duration: Int?, // 时长（秒）
    val webUrl: SpWebUrl?, // 网页链接信息
    val releaseTime: Long?, // 发布时间戳
    val playInfo: List<SpPlayInfo>?, // 播放信息列表（清晰度等）
    val campaign: Any?, // 活动信息（可为null）
    val waterMarks: Any?, // 水印信息（可为null）
    val ad: Boolean?, // 是否为广告
    val adTrack: List<AdTrackItem>?, // 广告跟踪列表
    val type: String?, // 类型（如"NORMAL"）
    val titlePgc: String?, // PGC标题（可为null）
    val descriptionPgc: String?, // PGC描述（可为null）
    val remark: String?, // 备注（可为null）
    val ifLimitVideo: Boolean?, // 是否限制播放
    val searchWeight: Int?, // 搜索权重
    val brandWebsiteInfo: Any?, // 品牌网站信息（可为null）
    val videoPosterBean: SpVideoPosterBean?, // 视频海报信息（可为null）
    val idx: Int?, // 索引
    val shareAdTrack: Any?, // 分享广告跟踪（可为null）
    val favoriteAdTrack: Any?, // 收藏广告跟踪（可为null）
    val webAdTrack: Any?, // 网页广告跟踪（可为null）
    val date: Long?, // 日期时间戳
    val promotion: Promotion?, // 推广信息
    val label: Label?, // 标签信息
    val labelList: List<LabelListItem>?, // 标签列表
    val descriptionEditor: String?, // 编辑描述
    val collected: Boolean?, // 是否已收藏
    val reallyCollected: Boolean?, // 是否真实收藏
    val played: Boolean?, // 是否已播放
    val subtitles: List<Any>?, // 字幕列表（空数组）
    val lastViewTime: Any?, // 最后观看时间（可为null）
    val playlists: Any?, // 播放列表（可为null）
    val src: Any?, // 来源（可为null）
    val recallSource: Any?, // 召回来源（可为null）
    val recall_source: Any? // 召回来源（可为null）
)
data class SpTag(
    val id: Int?,
    val name: String?, // 标签名称（如"时尚"、"上海"）
    val actionUrl: String?, // 跳转链接
    val adTrack: Any?, // 广告跟踪（可为null）
    val desc: String?, // 描述（可为null）
    val bgPicture: String?, // 背景图URL
    val headerImage: String?, // 头部图URL
    val tagRecType: String?, // 标签推荐类型（如"NORMAL"）
    val childTagList: Any?, // 子标签列表（可为null）
    val childTagIdList: Any?, // 子标签ID列表（可为null）
    val haveReward: Boolean?, // 是否有奖励
    val ifNewest: Boolean?, // 是否最新
    val newestEndTime: Any?, // 最新结束时间（可为null）
    val communityIndex: Int? // 社区索引
)
data class SpConsumption(
    val collectionCount: Int?, // 收藏数
    val shareCount: Int?, // 分享数
    val replyCount: Int?, // 回复数
    val realCollectionCount: Int? // 真实收藏数
)
data class SpProvider(
    val name: String?, // 名称（如"乐视"）
    val alias: String?, // 别名（如"letv"）
    val icon: String? // 图标URL
)
data class SpAuthor(
    val id: Int?,
    val icon: String?, // 作者头像URL
    val name: String?, // 作者名称
    val description: String?, // 作者描述
    val link: String?, // 链接
    val latestReleaseTime: Long?, // 最新发布时间
    val videoNum: Int?, // 视频数量
    val adTrack: Any?, // 广告跟踪（可为null）
    val follow: Follow?, // 关注状态
    val shield: Shield?, // 屏蔽状态
    val approvedNotReadyVideoCount: Int?, // 待审核视频数
    val ifPgc: Boolean?, // 是否为PGC
    val recSort: Int?, // 推荐排序
    val expert: Boolean? // 是否为专家
)
data class Follow(
    val itemType: String?, // 项目类型（如"author"）
    val itemId: Int?, // 项目ID
    val followed: Boolean? // 是否已关注
)
data class Shield(
    val itemType: String?, // 项目类型（如"author"）
    val itemId: Int?, // 项目ID
    val shielded: Boolean? // 是否已屏蔽
)
data class SpCover(
    val feed: String?, //  feed封面URL
    val detail: String?, // 详情页封面URL
    val blurred: String?, // 模糊封面URL
    val sharing: Any?, // 分享封面（可为null）
    val homepage: Any? // 首页封面（可为null）
)
data class SpWebUrl(
    val raw: String?, // 原始链接
    val forWeibo: String? // 微博专用链接
)
data class SpPlayInfo(
    val height: Int?, // 高度
    val width: Int?, // 宽度
    val urlList: List<UrlListItem>?, // 播放地址列表
    val name: String?, // 名称（如"标清"、"高清"）
    val type: String?, // 类型（如"normal"、"high"）
    val url: String? // 播放地址
)
data class UrlListItem(
    val name: String?, // 来源名称（如"aliyun"、"ucloud"）
    val url: String?, // 播放地址
    val size: Int? // 文件大小
)
data class AdTrackItem(
    val id: Int?, // ID
    val organization: String?, // 机构（如"miaozhen"）
    val viewUrl: String?, // 浏览跟踪URL
    val clickUrl: String?, // 点击跟踪URL
    val playUrl: String?, // 播放跟踪URL（可为空）
    val monitorPositions: Any?, // 监控位置（可为null）
    val needExtraParams: Any? // 是否需要额外参数（可为null）
)

data class SpVideoPosterBean(
    val scale: Double?, // 比例
    val url: String?, // 海报URL
    val fileSizeStr: String? // 文件大小描述
)


data class Promotion(
    val text: String? // 推广文本（如"广告"）
)


data class Label(
    val text: String?, // 文本（如"广告"）
    val card: String?, // 卡片文本（如"广告"）
    val detail: String? // 详情文本（如"广告"）
)


data class LabelListItem(
    val text: String?, // 文本（如"广告"）
    val actionUrl: String? // 跳转链接（可为null）
)
