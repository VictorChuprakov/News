package com.example.movies.news.ui.components

import NewsViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movies.shared.NewsCategory


@Composable
fun PagerCategory(navController: NavController, newsViewModel: NewsViewModel) {
    val listCategory = NewsCategory.values().toList()

    var selectedTabIndex by remember { mutableStateOf(0) }
    val currentCategory by newsViewModel.currentCategory.collectAsState()
    val loading by newsViewModel.isLoading.collectAsState()

    LaunchedEffect(currentCategory) {
        val index = listCategory.indexOfFirst { it.apiName == currentCategory }
        if (index != -1 && index != selectedTabIndex) {
            selectedTabIndex = index
        }
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
                    text = { Text(text = stringResource(id = category.displayNameResId)) }
                )
            }
        }
            NewsCategory(navController, newsViewModel.newsFlow.collectAsLazyPagingItems())
    }
}
