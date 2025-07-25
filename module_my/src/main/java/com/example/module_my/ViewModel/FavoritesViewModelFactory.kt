package com.example.module_my.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.Dao.CollectVideoDao
import com.example.data.Dao.FavoriteVideoDao

class FavoritesViewModelFactory(private val dao: FavoriteVideoDao) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            return FavoritesViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


class CollectViewModelFactory(private val dao: CollectVideoDao) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CollectViewModel::class.java)) {
            return CollectViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}