package com.example.movies.news.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.movies.news.presentation.main.NewsHomeViewModel

@Composable
fun NewsCategoryRow(newsHomeViewModel: NewsHomeViewModel) {
    val newsCategory = NewsCategory.entries
    val categoryNews = newsHomeViewModel.currentCategory.collectAsState()
    val lazyListState = rememberLazyListState()

    LaunchedEffect(categoryNews.value) {
        val index = newsCategory.indexOfFirst { it.apiName == categoryNews.value }
        if (index != -1) {
            lazyListState.animateScrollToItem(index)
        }
    }

    LazyRow(
        state = lazyListState,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(newsCategory) { category ->
            val isSelected = category.apiName == categoryNews.value
            Box(
                modifier = Modifier
                    .background(
                        if (isSelected)
                            MaterialTheme.colorScheme.primaryContainer
                        else MaterialTheme.colorScheme.secondaryContainer, shape = CircleShape
                    )
                    .border(
                        width = 1.4.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = CircleShape
                    )
                    .clickable { newsHomeViewModel.saveCategory(category.apiName) }
                    .padding(vertical = 5.dp, horizontal = 10.dp))
            {
                Text(
                    text = stringResource(category.displayNameResId),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}