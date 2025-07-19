package com.example.module_found.bean

/**
 *description:能看小说的app
 * author 王以飞
 * email 1206897770@qq.com
 * date 2025-2-18
 */
data class CategoryBean(
    val id: Int,
    val name: String,
    val alias: String?,
    val description: String,
    val bgPicture: String,
    val bgColor: String?,
    val headerImage: String,
    val defaultAuthorId: Int,
    val tagId: Int
)
