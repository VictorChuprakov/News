package com.example.movies.common.di

import android.content.Context
import androidx.room.Room
import com.example.movies.common.data.repository.FavoriteNewsRepositoryImpl
import com.example.movies.common.data.room.NewsDatabase
import com.example.movies.common.data.room.dao.FavoriteNewsDao
import com.example.movies.common.domain.FavoriteNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideFavoriteNewsDao(database: NewsDatabase): FavoriteNewsDao {
        return database.favoriteNewsDao()
    }

    @Provides
    fun provideDatabaseRepository(favoriteNewsDao: FavoriteNewsDao): FavoriteNewsRepository {
        return FavoriteNewsRepositoryImpl(favoriteNewsDao)
    }

}