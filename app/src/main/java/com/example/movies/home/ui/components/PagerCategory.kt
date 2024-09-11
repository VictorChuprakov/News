package com.example.movies.home.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movies.home.ui.NewsViewModel


@Composable
fun PagerCategory(navController: NavController, newsViewModel: NewsViewModel) {
    val listCategory =
        listOf("health", "entertainment", "sports", "politics", "society", "business")

    var selectedTabIndex by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState { listCategory.size }

    val pagerItems = newsViewModel.newsPagerFlow.collectAsLazyPagingItems()
    val current = newsViewModel.currentCategory.collectAsState()


    LaunchedEffect(current.value) {
        val currentCategory = current.value
        val newIndex = listCategory.indexOf(currentCategory)
        if (newIndex != -1 && newIndex != selectedTabIndex) {
            selectedTabIndex = newIndex
            pagerState.scrollToPage(newIndex)
        }
    }

    LaunchedEffect(selectedTabIndex) {
        val currentCategory = listCategory[selectedTabIndex]
        newsViewModel.updateCategory(currentCategory)
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }


    Column {
        ScrollableTabRow(selectedTabIndex = selectedTabIndex, edgePadding = 0.dp) {
            listCategory.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = item) }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            NewsCategory(navController, pagerItems)
        }
    }
}
