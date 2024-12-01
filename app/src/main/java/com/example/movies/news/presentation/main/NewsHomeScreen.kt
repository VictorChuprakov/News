package com.example.movies.news.presentation.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movies.common.presentation.components.ErrorDisplay
import com.example.movies.common.presentation.components.LoadingIndicator
import com.example.movies.news.presentation.main.components.NewsContent


@RequiresApi(Build.VERSION_CODES.O)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun HomeScreen(navController: NavController, newsHomeViewModel: NewsHomeViewModel = hiltViewModel()) {
    val newsState = newsHomeViewModel.newsFlow.collectAsLazyPagingItems()
    Column(modifier = Modifier.fillMaxSize().statusBarsPadding().padding(horizontal = 20.dp)) {
        when (newsState.loadState.refresh) {
            is LoadState.Loading -> {
                LoadingIndicator()
            }
            is LoadState.Error -> {
                val error = (newsState.loadState.refresh as LoadState.Error).error.localizedMessage
                if (error != null) {
                    ErrorDisplay(
                        error = error,
                        onRetryClick = { newsState.retry() }
                    )
                }
            }
            else -> {
                NewsContent(navController, newsState, newsHomeViewModel)
            }
        }
    }
}
