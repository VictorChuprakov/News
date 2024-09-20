package com.example.movies.common.ui.bottomNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Feed
import androidx.compose.material.icons.automirrored.outlined.Feed
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import com.example.movies.R

object BottomConstants {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = R.string.label_news,
            route = RoutesNavBottom.news,
            selectedIcon = Icons.AutoMirrored.Filled.Feed,
            unselectedIcon = Icons.AutoMirrored.Outlined.Feed
        ),
        BottomNavItem(
            label = R.string.label_favorite,
            route = RoutesNavBottom.favorite,
            selectedIcon = Icons.Filled.Bookmark,
            unselectedIcon = Icons.Outlined.BookmarkBorder
        )
    )
}
