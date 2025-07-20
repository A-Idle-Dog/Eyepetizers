package com.example.module_video.model

/*
* 评论API响应顶层数据结构
*
* @property adExist 是否存在广告
* @property count 当前返回的评论数量
* @property itemList 评论项列表
* @property nextPageUrl 下一页数据的URL（用于分页加载）
* @property total 总评论数
*/
data class CommentResponse(
    val adExist: Boolean,
    val count: Int,
    val itemList: List<Comment>,
    val nextPageUrl: String?,
    val total: Int
)

/*
* 评论项基类
*
* @property adIndex 广告索引（-1表示非广告）
* @property data 评论数据内容
* @property id 评论项ID
* @property tag 标签信息（通常为null）
* @property trackingData 追踪数据（通常为null）
* @property type 评论项类型（如"reply"）
*/
data class Comment(
    val adIndex: Int,
    val `data`: CommentData,  // 使用反引号转义关键字
    val id: Long,
    val tag: Any?,
    val trackingData: Any?,
    val type: String
)

/*
* 评论数据内容
*
* @property actionUrl 点击跳转URL
* @property adTrack 广告追踪信息（通常为null）
* @property cover 封面图URL
* @property createTime 评论创建时间（Unix时间戳，毫秒）
* @property dataType 数据类型（如"ReplyBeanForClient"）
* @property font 字体样式
* @property hot 是否为热门评论
* @property id 评论ID
* @property imageUrl 图片URL
* @property likeCount 点赞数
* @property liked 当前用户是否已点赞
* @property message 评论内容
* @property parentReply 父级评论信息（用于嵌套回复）
* @property parentReplyId 父评论ID
* @property recommendLevel 推荐级别
* @property replyStatus 评论状态（如"PUBLISHED"）
* @property rootReplyId 根评论ID
* @property sequence 排序序号
* @property showConversationButton 是否显示对话按钮
* @property showParentReply 是否显示父级回复
* @property sid 评论字符串ID
* @property text 文本内容（部分类型使用）
* @property type 评论类型（如"video"）
* @property ugcVideoId UGC视频ID
* @property ugcVideoUrl UGC视频URL
* @property user 评论用户信息
* @property userBlocked 用户是否被屏蔽
* @property userType 用户类型
* @property videoId 关联的视频ID
* @property videoTitle 视频标题
*/
data class CommentData(
    val actionUrl: String?,
    val adTrack: Any?,
    val cover: Any?,
    val createTime: Long,//评论时间
    val dataType: String,
    val font: String?,
    val hot: Boolean,
    val id: Long,
    val imageUrl: String?,
    val likeCount: Int,//点赞数
    val liked: Boolean,
    val message: String,//评论信息
    val parentReply: ParentReply?,
    val parentReplyId: Long?,
    val recommendLevel: String?,
    val replyStatus: String,
    val rootReplyId: Long?,
    val sequence: Int,
    val showConversationButton: Boolean,
    val showParentReply: Boolean,
    val sid: String,
    val text: String?,
    val type: String?,
    val ugcVideoId: Any?,
    val ugcVideoUrl: Any?,
    val user: CommentUser,
    val userBlocked: Boolean,
    val userType: Any?,
    val videoId: Long,
    val videoTitle: String
)

/*
* 父级评论信息
*
* @property id 父评论ID
* @property imageUrl 图片URL
* @property message 评论内容
* @property replyStatus 评论状态
* @property user 用户信息
*/
data class ParentReply(
    val id: Long,
    val imageUrl: Any?,
    val message: String,
    val replyStatus: String,
    val user: CommentUser
)

/*
* 评论用户信息
*
* @property actionUrl 用户主页URL
* @property area 地区信息
* @property avatar 头像URL
* @property birthday 生日时间戳
* @property city 所在城市
* @property country 所在国家
* @property cover 封面图URL
* @property description 个人描述
* @property expert 是否为专家用户
* @property followed 是否已关注
* @property gender 性别（male/female）
* @property ifPgc 是否为PGC用户
* @property job 职业
* @property library 库信息
* @property limitVideoOpen 视频限制状态
* @property nickname 昵称
* @property registDate 注册时间戳
* @property releaseDate 发布时间戳
* @property uid 用户ID
* @property university 大学信息
* @property userType 用户类型（NORMAL等）
*/
data class CommentUser(
    val actionUrl: String?,
    val area: Any?,//ip
    val avatar: String?,//头像
    val birthday: Long?,
    val city: String?,
    val country: String?,
    val cover: String?,
    val description: String?,
    val expert: Boolean,
    val followed: Boolean,
    val gender: String?,
    val ifPgc: Boolean,
    val job: String?,
    val library: String?,
    val limitVideoOpen: Boolean,
    val nickname: String,//名字
    val registDate: Long?,
    val releaseDate: Long?,
    val uid: Long,
    val university: String?,
    val userType: String
)