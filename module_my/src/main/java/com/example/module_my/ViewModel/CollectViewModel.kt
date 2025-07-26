package com.example.module_my.ViewModel

import androidx.lifecycle.ViewModel
import com.example.data.Dao.CollectVideoDao

class CollectViewModel(private val dao: CollectVideoDao) : ViewModel() {
    val collectVideo = dao.getAllFavorites()
}