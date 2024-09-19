package com.example.movies

import android.app.Application
import com.example.movies.common.data.api.RetrofitProvider
import com.example.movies.common.data.room.DatabaseProvider

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DatabaseProvider.init(this)
    }
}