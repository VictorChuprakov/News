package com.example.movies.home.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.example.movies.home.data.News

@Composable
fun NewsCategory(
    navController: NavController,
    listNews: LazyPagingItems<News>,
) {
    LazyColumn {
        items(listNews.itemCount) { index ->
            val news = listNews[index]
            news?.let {
                CardNews(navController, news)
            }
        }
    }
}
