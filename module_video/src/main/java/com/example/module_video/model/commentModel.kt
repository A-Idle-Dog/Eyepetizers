package com.example.module_video.model

data class CommentResponse(
    val adExist: Boolean,
    val count: Int,
    val itemList: List<Comment>,
    val nextPageUrl: String?,
    val total: Int
)

data class Comment(
    val adIndex: Int,
    val `data`: CommentData,  // 使用反引号转义关键字
    val id: Long,
    val tag: Any?,
    val trackingData: Any?,
    val type: String
)

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

data class ParentReply(
    val id: Long,
    val imageUrl: Any?,
    val message: String,
    val replyStatus: String,
    val user: CommentUser
)

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