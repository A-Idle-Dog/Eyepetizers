package com.example.module_video.model

data class OthersData(
    val adExist: Boolean,
    val count: Int,
    val itemList: List<Item>,
    val nextPageUrl: Any,
    val total: Int
)

data class Item(
    val adIndex: Int,
    val `data`: Data,
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String
)

data class Data(
    val actionUrl: String,
    val ad: Boolean,
    val adTrack: List<Any>,
    val author: Author,
    val brandWebsiteInfo: Any,
    val campaign: Any,
    val category: String,
    val collected: Boolean,
    val consumption: Consumption,
    val cover: Cover,
    val dataType: String,
    val date: Long,
    val description: String,//描述
    val descriptionEditor: String,
    val descriptionPgc: String,
    val duration: Int,
    val favoriteAdTrack: Any,
    val follow: Any,
    val id: Int,//数据id
    val idx: Int,
    val ifLimitVideo: Boolean,
    val label: Any,
    val labelList: List<Any>,
    val lastViewTime: Any,
    val library: String,
    val playInfo: List<PlayInfo>,
    val playUrl: String,
    val played: Boolean,
    val playlists: Any,
    val promotion: Any,
    val provider: Provider,
    val reallyCollected: Boolean,
    val recallSource: Any,
    val recall_source: Any,
    val releaseTime: Long,
    val remark: String,
    val resourceType: String,
    val searchWeight: Int,
    val shareAdTrack: Any,
    val slogan: Any,
    val src: Any,
    val subTitle: Any,
    val subtitles: List<Any>,
    val tags: List<Tag>,//标签
    val text: String,
    val thumbPlayUrl: Any,
    val title: String,
    val titlePgc: String,
    val type: String,
    val videoPosterBean: VideoPosterBean,
    val waterMarks: Any,
    val webAdTrack: Any,
    val webUrl: WebUrl
)

data class Author(
    val adTrack: Any,
    val approvedNotReadyVideoCount: Int,
    val description: String,
    val expert: Boolean,
    val follow: Follow,
    val icon: String,//作者头像URL
    val id: Int,
    val ifPgc: Boolean,
    val latestReleaseTime: Long,
    val link: String,
    val name: String,//作者名字
    val recSort: Int,
    val shield: Shield,
    val videoNum: Int
)

data class Consumption(
    val collectionCount: Int,//收藏数
    val realCollectionCount: Int,//真实收藏数
    val replyCount: Int,
    val shareCount: Int
)

data class Cover(
    val blurred: String,
    val detail: String,
    val feed: String,//信息流封面图URL
    val homepage: String,
    val sharing: Any
)

data class PlayInfo(
    val height: Int,
    val name: String,
    val type: String,
    val url: String,
    val urlList: List<Url>,
    val width: Int
)

data class Provider(
    val alias: String,
    val icon: String,
    val name: String
)

data class Tag(
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
    val newestEndTime: Any,
    val tagRecType: String
)

data class VideoPosterBean(
    val fileSizeStr: String,
    val scale: Double,
    val url: String
)

data class WebUrl(
    val forWeibo: String,
    val raw: String
)

data class Follow(
    val followed: Boolean,
    val itemId: Int,
    val itemType: String
)

data class Shield(
    val itemId: Int,
    val itemType: String,
    val shielded: Boolean
)

data class Url(
    val name: String,
    val size: Int,
    val url: String
)