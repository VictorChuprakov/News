package com.example.movies.news.ui

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movies.common.data.api.RetrofitProvider
import com.example.movies.common.data.dataprefence.DataPreference
import com.example.movies.common.ui.components.HeaderDefault
import com.example.movies.news.ui.components.PagerCategory


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun NewsScreen(navController: NavController) {
    val context = LocalContext.current
    val dataPreference = remember { DataPreference(context) }
    val newsViewModel: NewsViewModel =
        viewModel(factory = NewsViewModelFactory(RetrofitProvider.newsRepository, dataPreference))

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        HeaderDefault()
        PagerCategory(
            navController = navController,
            newsViewModel = newsViewModel
        )
    }
}