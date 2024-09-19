package com.example.movies.common.ui.bottomNavigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.wear.compose.material.Text


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
                        imageVector = if (currentRoute == navItem.route) navItem.selectedIcon else navItem.unselectedIcon,
                        contentDescription = null,
                        modifier = if (currentRoute == navItem.route) Modifier
                            .scale(1.0f)
                            .size(25.dp) else Modifier
                            .scale(1.1f)
                            .size(25.dp),
                        tint =  MaterialTheme.colorScheme.primary
                    )
                },
                label = { Text(text = stringResource(id = navItem.label), color = MaterialTheme.colorScheme.primary) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                ),
                alwaysShowLabel = false
            )
        }
    }
}
