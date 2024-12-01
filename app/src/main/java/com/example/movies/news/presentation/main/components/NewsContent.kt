package com.example.movies.news.presentation.main.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.example.movies.news.data.main.model.NewsItem
import com.example.movies.news.presentation.main.NewsHomeViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsContent(
    navController: NavController,
    listNews: LazyPagingItems<NewsItem>,
    newsHomeViewModel: NewsHomeViewModel
) {
    Column{
        NewsHeader()
        Spacer(modifier = Modifier.height(20.dp))
        NewsSearch()
        Spacer(modifier = Modifier.height(20.dp))
        NewsCategoryRow(newsHomeViewModel)
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            items(listNews.itemCount) { index ->
                val news = listNews[index]
                if (news != null) {
                    CardNews(navController, news)
                }
            }
        }
    }
}
