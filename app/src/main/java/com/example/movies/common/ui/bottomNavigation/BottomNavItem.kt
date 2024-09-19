package com.example.movies.common.ui.bottomNavigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val label: Int,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

