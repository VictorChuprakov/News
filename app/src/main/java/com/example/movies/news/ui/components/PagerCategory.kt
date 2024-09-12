package com.example.movies.news.ui.components

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movies.news.ui.NewsViewModel


@Composable
fun PagerCategory(navController: NavController, newsViewModel: NewsViewModel) {
    val listCategory = listOf(
        "politics", "economics", "business", "society", "science",
        "technology", "culture", "sports", "entertainment", "health",
        "natural_events", "nature", "incidents", "education", "crime",
        "religion", "international_relations"
    )

    var selectedTabIndex by remember { mutableStateOf(0) }
    val currentCategory by newsViewModel.currentCategory.collectAsState()

    // Update selectedTabIndex only if necessary
    LaunchedEffect(currentCategory) {
        val index = listCategory.indexOf(currentCategory)
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
                        // Update the selected tab and category in ViewModel
                        if (index != selectedTabIndex) {
                            selectedTabIndex = index
                            newsViewModel.saveCategory(category)
                        }
                    },
                    text = { Text(text = category) }
                )
            }
        }
        // Ensure NewsCategory correctly handles LazyPagingItems
        NewsCategory(navController, newsViewModel.newsFlow.collectAsLazyPagingItems())
    }
}
