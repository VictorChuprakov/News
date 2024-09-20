package com.example.movies.common.data.dataprefence

import android.content.Context
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
    fun provideDataPreference(@ApplicationContext context: Context):DataPreference{
        return DataPreference(context)
    }
}