package com.example.module_my.ViewModel

import androidx.lifecycle.ViewModel
import com.example.data.Dao.FavoriteVideoDao

class FavoritesViewModel(private val dao: FavoriteVideoDao) :ViewModel(){
    val favoriteVideos = dao.getAllFavorites()
}