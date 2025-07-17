package com.example.module_home.model

data class  rApiResponse(
    val count: Int,
    val itemList: List<Recommend>
)

data class Recommend(
    val `data`: rData,
    val type: String
)

data class rData(
    val actionUrl: String,
    val ad: Boolean,
    val author: rAuthor,
    val category: String,
    val collected: Boolean,
    val consumption: rConsumption,
    val content: rContent,
    val cover: CoverX,
    val dataType: String,
    val date: Long,
    val description: String,
    val descriptionEditor: String,
    val duration: Int,
    val header: rHeader,
    val id: Int,
    val idx: Int,
    val ifLimitVideo: Boolean,
    val library: String,
    val playInfo: List<PlayInfoX>,
    val playUrl: String,
    val played: Boolean,
    val provider: ProviderX,
    val reallyCollected: Boolean,
    val recallSource: String,
    val recall_source: String,
    val releaseTime: Long,
    val remark: String,
    val resourceType: String,
    val searchWeight: Int,
    val src: Int,
    val tags: List<TagX>,
    val text: String,
    val title: String,
    val type: String,
    val videoPosterBean: VideoPosterBeanX,
    val webUrl: WebUrlX
)

data class rAuthor(
    val approvedNotReadyVideoCount: Int,
    val description: String,
    val expert: Boolean,
    val follow: rFollow,
    val icon: String,
    val id: Int,
    val ifPgc: Boolean,
    val latestReleaseTime: Long,
    val link: String,
    val name: String,
    val recSort: Int,
    val shield: Shield,
    val videoNum: Int
)

data class rConsumption(
    val collectionCount: Int,
    val realCollectionCount: Int,
    val replyCount: Int,
    val shareCount: Int
)

data class rContent(
    val adIndex: Int,
    val `data`: rDataX,
    val id: Int,
    val type: String
)

data class CoverX(
    val blurred: String,
    val detail: String,
    val feed: String,
)

data class rHeader(
    val actionUrl: String,
    val description: String,
    val icon: String,
    val iconType: String,
    val id: Int,
    val showHateVideo: Boolean,
    val textAlign: String,
    val time: Long,
    val title: String
)

data class PlayInfoX(
    val height: Int,
    val name: String,
    val type: String,
    val url: String,
    val urlList: List<rUrl>,
    val width: Int
)

data class ProviderX(
    val alias: String,
    val icon: String,
    val name: String
)

data class TagX(
    val actionUrl: String,
    val bgPicture: String,
    val communityIndex: Int,
    val desc: String,
    val haveReward: Boolean,
    val headerImage: String,
    val id: Int,
    val ifNewest: Boolean,
    val name: String,
    val tagRecType: String
)

data class VideoPosterBeanX(
    val fileSizeStr: String,
    val scale: Double,
    val url: String
)

data class WebUrlX(
    val forWeibo: String,
    val raw: String
)

data class rFollow(
    val followed: Boolean,
    val itemId: Int,
    val itemType: String
)

data class Shield(
    val itemId: Int,
    val itemType: String,
    val shielded: Boolean
)

data class rDataX(
    val ad: Boolean,
    val author: Author,
    val category: String,
    val collected: Boolean,
    val consumption: Consumption,
    val cover: CoverX,
    val dataType: String,
    val date: Long,
    val description: String,
    val descriptionEditor: String,
    val descriptionPgc: String,
    val duration: Int,
    val id: Int,
    val idx: Int,
    val ifLimitVideo: Boolean,
    val label: Any,
    val library: String,
    val playInfo: List<PlayInfoX>,
    val playUrl: String,
    val played: Boolean,
    val provider: ProviderX,
    val reallyCollected: Boolean,
    val recallSource: String,
    val recall_source: String,
    val releaseTime: Long,
    val remark: String,
    val resourceType: String,
    val searchWeight: Int,
    val src: Int,
    val tags: List<TagX>,
    val title: String,
    val titlePgc: String,
    val type: String,
    val videoPosterBean: VideoPosterBeanX,
    val webUrl: WebUrlX
)

data class rUrl(
    val name: String,
    val size: Int,
    val url: String
)