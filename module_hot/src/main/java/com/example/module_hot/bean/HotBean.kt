package com.example.module_hot.bean

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
data class HotBean(
    val itemList: List<VideoItem>?, // 视频项列表
    val count: Int?, // 当前返回数量
    val total: Int?, // 总数量（此处为0）
    val nextPageUrl: String?, // 下一页URL（此处为null）
    val adExist: Boolean? // 是否存在广告（此处为false）
)
data class VideoItem(
    val type: String?, // 类型（固定为"video"）
    val data: VideoBeanForClient?, // 视频核心数据
    val trackingData: Any?, // 跟踪数据（JSON中为null）
    val tag: Any?, // 标签（JSON中为null）
    val id: Int?, // 标识（此处为0）
    val adIndex: Int? // 广告索引（此处为-1，非广告）
)
data class VideoBeanForClient(
    val dataType: String?, // 数据类型（固定为"VideoBeanForClient"）
    val id: Int?, // 视频唯一标识
    val title: String?, // 视频标题
    val description: String?, // 视频描述
    val library: String?, // 所属资源库（如"DAILY"）
    val tags: List<Tag>?, // 视频标签列表
    val consumption: VideoConsumption?, // 视频消费数据（收藏/分享等）
    val resourceType: String?, // 资源类型（固定为"video"）
    val slogan: String?, // 口号（可为null或空字符串）
    val provider: Provider?, // 视频提供商信息
    val category: String?, // 视频分类（如"创意"、"生活"）
    val author: Author?, // 视频作者信息
    val cover: Cover?, // 视频封面图信息
    val playUrl: String?, // 播放地址URL
    val thumbPlayUrl: String?, // 缩略图播放地址（可为null）
    val duration: Int?, // 视频时长（秒）
    val webUrl: WebUrl?, // 网页链接信息
    val releaseTime: Long?, // 发布时间戳
    val playInfo: List<PlayInfo>?, // 播放信息列表（清晰度/地址等）
    val campaign: Any?, // 活动信息（可为null）
    val waterMarks: Any?, // 水印信息（可为null）
    val ad: Boolean?, // 是否为广告（此处为false）
    val adTrack: List<AdTrackItem>?, // 广告跟踪列表（此处为空）
    val type: String?, // 视频类型（固定为"NORMAL"）
    val titlePgc: String?, // PGC标题（可为null或空字符串）
    val descriptionPgc: String?, // PGC描述（可为null或空字符串）
    val remark: String?, // 备注（可为null）
    val ifLimitVideo: Boolean?, // 是否限制播放（此处为false）
    val searchWeight: Int?, // 搜索权重（此处为0）
    val brandWebsiteInfo: Any?, // 品牌网站信息（可为null）
    val videoPosterBean: Any?, // 视频海报信息（可为null）
    val idx: Int?, // 索引（此处为0）
    val shareAdTrack: Any?, // 分享广告跟踪（可为null）
    val favoriteAdTrack: Any?, // 收藏广告跟踪（可为null）
    val webAdTrack: Any?, // 网页广告跟踪（可为null）
    val date: Long?, // 日期时间戳（与releaseTime一致）
    val promotion: Any?, // 推广信息（可为null）
    val label: Any?, // 标签（可为null）
    val labelList: List<Any>?, // 标签列表（此处为空）
    val descriptionEditor: String?, // 编辑描述（详细内容）
    val collected: Boolean?, // 是否已收藏（此处为false）
    val reallyCollected: Boolean?, // 是否真实收藏（此处为false）
    val played: Boolean?, // 是否已播放（此处为false）
    val subtitles: List<Any>?, // 字幕列表（此处为空）
    val lastViewTime: Any?, // 最后观看时间（可为null）
    val playlists: Any?, // 播放列表（可为null）
    val src: Any?, // 来源（可为null）
    val recallSource: Any?, // 召回来源（可为null）
    val recall_source: Any? // 召回来源（可为null，与recallSource一致）
)
data class Tag(
    val id: Int?, // 标签唯一标识
    val name: String?, // 标签名称（如"每日创意灵感"、"唯美"）
    val actionUrl: String?, // 标签跳转链接
    val adTrack: Any?, // 广告跟踪（可为null）
    val desc: String?, // 标签描述（可为null或具体说明）
    val bgPicture: String?, // 背景图URL
    val headerImage: String?, // 头部图URL
    val tagRecType: String?, // 标签推荐类型（如"IMPORTANT"、"NORMAL"）
    val childTagList: Any?, // 子标签列表（可为null）
    val childTagIdList: Any?, // 子标签ID列表（可为null）
    val haveReward: Boolean?, // 是否有奖励（此处为false）
    val ifNewest: Boolean?, // 是否为最新（此处为false）
    val newestEndTime: Any?, // 最新结束时间（可为null）
    val communityIndex: Int? // 社区索引（此处为0）
)
data class VideoConsumption(
    val collectionCount: Int?, // 收藏数
    val shareCount: Int?, // 分享数
    val replyCount: Int?, // 回复数
    val realCollectionCount: Int? // 真实收藏数（去重后）
)
data class Provider(
    val name: String?, // 提供商名称（如"Vimeo"、"YouTube"）
    val alias: String?, // 提供商别名（如"vimeo"、"youtube"）
    val icon: String? // 提供商图标URL
)
data class Author(
    val id: Int?, // 作者唯一标识
    val icon: String?, // 作者头像URL
    val name: String?, // 作者名称（如"全球创意视频精选"）
    val description: String?, // 作者描述
    val link: String?, // 作者链接（可为空字符串）
    val latestReleaseTime: Long?, // 最新发布时间戳
    val videoNum: Int?, // 作者发布的视频总数
    val adTrack: Any?, // 广告跟踪（可为null）
    val follow: Follow?, // 关注状态信息
    val shield: Shield?, // 屏蔽状态信息
    val approvedNotReadyVideoCount: Int?, // 待审核视频数（此处为0）
    val ifPgc: Boolean?, // 是否为PGC（专业生成内容，此处为true）
    val recSort: Int?, // 推荐排序（此处为0）
    val expert: Boolean? // 是否为专家（此处为false）
)
data class Follow(
    val itemType: String?, // 项目类型（如"author"）
    val itemId: Int?, // 项目ID（与作者ID一致）
    val followed: Boolean? // 是否已关注（此处为false）
)
data class Shield(
    val itemType: String?, // 项目类型（如"author"）
    val itemId: Int?, // 项目ID（与作者ID一致）
    val shielded: Boolean? // 是否已屏蔽（此处为false）
)
data class Cover(
    val feed: String?, // feed流中显示的封面图URL
    val detail: String?, // 详情页显示的封面图URL
    val blurred: String?, // 模糊封面图URL（用于背景等）
    val sharing: Any?, // 分享用封面图（可为null）
    val homepage: String? // 首页用封面图URL（可为null或具体地址）
)
data class WebUrl(
    val raw: String?, // 原始网页链接
    val forWeibo: String? // 微博专用分享链接
)
data class PlayInfo(
    val height: Int?, // 视频高度（像素）
    val width: Int?, // 视频宽度（像素）
    val urlList: List<UrlListItem>?, // 播放地址列表（多来源）
    val name: String?, // 清晰度名称（如"高清"）
    val type: String?, // 类型（如"high"）
    val url: String? // 播放地址（主地址）
)
data class UrlListItem(
    val name: String?, // 来源名称（如"aliyun"、"ucloud"）
    val url: String?, // 该来源的播放地址
    val size: Int? // 文件大小（字节）
)
data class AdTrackItem(
    // 注：当前JSON中adTrack为空数组，字段参考历史结构定义
    val id: Int?, // 跟踪ID
    val organization: String?, // 所属机构
    val viewUrl: String?, // 浏览跟踪URL
    val clickUrl: String?, // 点击跟踪URL
    val playUrl: String?, // 播放跟踪URL
    val monitorPositions: Any?, // 监控位置
    val needExtraParams: Any? // 是否需要额外参数
)
