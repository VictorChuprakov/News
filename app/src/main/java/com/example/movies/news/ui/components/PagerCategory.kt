package com.example.movies.news.ui.components

import NewsViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems


@Composable
fun PagerCategory(navController: NavController, newsViewModel: NewsViewModel) {
    val listCategory = NewsCategoryClass.entries
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val currentCategory by newsViewModel.currentCategory.collectAsState()
    val listState = rememberLazyListState()
    val lazyPagingItems = newsViewModel.newsFlow.collectAsLazyPagingItems()

    LaunchedEffect(currentCategory) {
        val index = listCategory.indexOfFirst { it.apiName == currentCategory }
        if (index != -1 && index != selectedTabIndex) {
            selectedTabIndex = index
        }
        listState.scrollToItem(0)
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
                            lazyPagingItems.refresh()
                        }
                    },
                    text = { Text(text = stringResource(id = category.displayNameResId), color = MaterialTheme.colorScheme.primary) }
                )
            }
        }
        NewsCategory(navController, lazyPagingItems, listState)
    }
}