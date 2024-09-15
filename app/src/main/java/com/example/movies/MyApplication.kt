package com.example.movies

import android.app.Application
import com.example.movies.shared.data.di.RetrofitProvider

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        RetrofitProvider.init(this)
    }
}