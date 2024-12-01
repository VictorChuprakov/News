package com.example.movies.common.presentation.bottomNavigation

import com.example.movies.R
import com.example.movies.navigation.Routes

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
) {
    data object Home : BottomBarScreen(
        route = Routes.Home.route,
        title = "Home",
        icon = R.drawable.ic_nav_home,
    )

    data object Details : BottomBarScreen(
        route = Routes.Favorite.route,
        title = "Favorite",
        icon = R.drawable.ic_nav_favorite,
    )

    data object Profile : BottomBarScreen(
        route = Routes.Profile.route,
        title = "Profile",
        icon = R.drawable.ic_nav_profile,
    )
}
