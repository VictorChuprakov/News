package com.example.movies.news.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.example.movies.news.data.model.News

@Composable
fun NewsCategory(
    navController: NavController,
    listNews: LazyPagingItems<News>,
    listState: LazyListState
) {
    LazyColumn(state = listState) {
        items(listNews.itemCount) { index ->
            val news = listNews[index]
            news?.let {
                CardNews(navController, news)
            }
        }
    }
}
