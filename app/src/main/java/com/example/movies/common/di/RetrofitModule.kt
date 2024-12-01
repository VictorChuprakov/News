package com.example.movies.common.di

import com.example.movies.common.data.api.ApiConstants
import com.example.movies.common.data.api.ApiService
import com.example.movies.news.data.details.repository.GetNewsByIdRepositoryImpl
import com.example.movies.news.domain.details.GetNewsByIdRepository
import com.example.movies.news.data.main.repository.GetNewsRepositoryImpl
import com.example.movies.news.domain.main.GetNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun providerRetrofit(): Retrofit {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideGetNewsRepositoryById(apiService: ApiService): GetNewsByIdRepository {
        return GetNewsByIdRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideGetNewsRepository(apiService: ApiService): GetNewsRepository {
        return GetNewsRepositoryImpl(apiService)
    }


}