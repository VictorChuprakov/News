package com.example.movies.news.ui

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movies.common.ui.components.HeaderDefault
import com.example.movies.news.ui.components.PagerCategory


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun NewsScreen(navController: NavController) {
    val newsViewModel: NewsViewModel = hiltViewModel()

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