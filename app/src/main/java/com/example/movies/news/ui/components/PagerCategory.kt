package com.example.movies.news.ui.components

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movies.R
import com.example.movies.common.ui.components.ShowToast
import com.example.movies.news.ui.NewsViewModel
import java.io.IOException


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun PagerCategory(navController: NavController, newsViewModel: NewsViewModel) {
    val listCategory = NewsCategoryClass.entries
    val currentCategory by newsViewModel.currentCategory.collectAsState()
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val listState = rememberLazyListState()
    val newsState = newsViewModel.newsFlow.collectAsLazyPagingItems()

    LaunchedEffect(currentCategory) {
        val index = listCategory.indexOfFirst { it.apiName == currentCategory }
        if (index != -1 && index != selectedTabIndex) {
            selectedTabIndex = index
        }
        listState.animateScrollToItem(0)
    }

    Column {
        ScrollableTabRow(selectedTabIndex = selectedTabIndex, edgePadding = 0.dp) {
            listCategory.forEachIndexed { index, category ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        if (index != selectedTabIndex) {
                            selectedTabIndex = index
                            newsViewModel.saveCategory(category.apiName)
                        }
                    },
                    text = {
                        Text(
                            text = stringResource(id = category.displayNameResId),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                )
            }
        }
        when (val refreshState = newsState.loadState.refresh) {
            is LoadState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is LoadState.Error -> {
                val error = refreshState.error
                val errorMessage = when (error) {
                    is IOException -> stringResource(id = R.string.network_error)
                    is HttpException -> stringResource(id = R.string.request_failed)
                    else -> stringResource(id = R.string.unexpected_error)
                }
               ShowToast(message = errorMessage)
            }
            else -> {
                NewsCategory(navController, newsState, listState)
            }
        }
    }
}
