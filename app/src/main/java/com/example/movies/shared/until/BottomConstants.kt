package com.example.movies.shared.until

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Feed
import androidx.compose.material.icons.automirrored.outlined.Feed
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder


object BottomConstants {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "News",
            route = Routes.News,
            selectedIcon = Icons.AutoMirrored.Filled.Feed,
            unselectedIcon = Icons.AutoMirrored.Outlined.Feed
        ),
        BottomNavItem(
            label = "Favorite",
            route = Routes.Favorite,
            selectedIcon = Icons.Filled.Bookmark,
            unselectedIcon = Icons.Outlined.BookmarkBorder
        )
    )
}