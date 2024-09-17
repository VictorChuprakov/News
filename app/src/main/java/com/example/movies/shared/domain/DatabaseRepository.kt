package com.example.movies.shared.domain

import com.example.movies.shared.data.room.FavoriteNewEntity
import com.example.movies.shared.data.room.FavoriteNewsDao
import kotlinx.coroutines.flow.Flow

class DatabaseRepository(private val favoriteNewsDao: FavoriteNewsDao) {

    val allFavorites: Flow<List<FavoriteNewEntity>> = favoriteNewsDao.getAllFavorite()

    //добавление
    suspend fun insert(news: FavoriteNewEntity) {
        favoriteNewsDao.insertFavorite(news)
    }

    //удаление из экрана favorite
    suspend fun delete(apiId: Int) {
        favoriteNewsDao.deleteByApiId(apiId)
    }
    //удаление из экрана News
    suspend fun deleteFavorite(id: Int){
        favoriteNewsDao.deleteNewsById(id)
    }
    //проверка уже есть такая избранная новость или нет
    suspend fun isFavorite(apiId: Int): Boolean {
        return favoriteNewsDao.getFavoriteByApiId(apiId) != null
    }
    //получение новости по айди в favoriteDetails
    suspend fun getNewsDetailsById(id: Int): FavoriteNewEntity {
        return favoriteNewsDao.getNewsById(id)

    }
}