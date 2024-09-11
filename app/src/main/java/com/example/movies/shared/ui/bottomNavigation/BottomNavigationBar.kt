package com.example.movies.shared.ui.bottomNavigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movies.shared.until.BottomConstants


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar(containerColor = Color.Transparent) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        BottomConstants.BottomNavItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = { navController.navigate(navItem.route) },
                icon = {
                    Icon(
                        imageVector = if (currentRoute == navItem.route) navItem.selectedIcon else navItem.unselectedIcon
                        ,
                        contentDescription = "icon bottomNavigation",
                        modifier = Modifier
                            .size(24.dp)
                    )
                },
                label = { Text(text = navItem.label) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                ),
            )
        }
    }
}
