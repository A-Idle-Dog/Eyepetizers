package com.example.module_video.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class invokeitem(
    val playuri: String,
    val cover: String,
    val id: Int,
    val title: String,
    val author: String,
    val authoricon: String,
    val tags: String,
    val des:String,
    val likecount: Int,
    val collectcount: Int
) : Parcelable