package com.example.movies.common.di

import android.content.Context
import com.example.movies.common.data.dataprefence.DataPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataPreferenceModule {

    @Provides
    @Singleton
    fun provideDataPreference(@ApplicationContext context: Context): DataPreference {
        return DataPreference(context)
    }
}