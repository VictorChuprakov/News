package com.example.movies.shared.until

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SavedSearch
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search

object BottomConstants {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "News",
            route = Routes.Home,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavItem(
            label = "Search",
            route = Routes.Search,
            selectedIcon = Icons.Filled.SavedSearch,
            unselectedIcon = Icons.Outlined.Search
        ),
        BottomNavItem(
            label = "Favourites",
            route = Routes.Favorite,
            selectedIcon = Icons.Filled.Bookmark,
            unselectedIcon = Icons.Outlined.BookmarkBorder
        )
    )
}