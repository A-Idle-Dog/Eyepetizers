package com.example.photo.bean

data class photoData (
    val itemList: List<String>,
    val icon :String,
    val author : String,
    val title:String,
    val likecount:Int,
    val collectcount:Int,
    val tag:String,
    val isliked:Boolean,
    val uid:Int,
)