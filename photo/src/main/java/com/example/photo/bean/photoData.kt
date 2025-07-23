package com.example.photo.bean

data class photoData (
    val itemList: List<String>,
    val icon :String,
    val author : String,
    val title:String,
    val likecount:Int,
    val collectcount:Int,
    val tag:String,
    var isliked:Boolean,
    val uid:Int,
    val createTime:String,
    val city:String,
)