package com.example.module_my.ViewModel

import androidx.lifecycle.ViewModel
import com.example.data.Dao.FavoriteVideoDao

class FavoritesViewModel(Dao : FavoriteVideoDao): ViewModel() {
    val favoriteVideos = Dao.getAllFavorites()
}