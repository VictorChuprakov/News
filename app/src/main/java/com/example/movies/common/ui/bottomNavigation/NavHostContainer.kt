package com.example.movies.common.ui.bottomNavigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.movies.details.ui.DetailsScreen
import com.example.movies.favourites.ui.FavoritesDetailsScreen
import com.example.movies.favourites.ui.FavoritesScreen
import com.example.movies.news.ui.NewsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavHostContainer(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            if (navController.currentBackStackEntryAsState().value?.destination?.route != "${RoutesNavBottom.Details}/{id}") {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = RoutesNavBottom.News,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                route = "${RoutesNavBottom.Details}/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id")
                id?.let { DetailsScreen(navController, it) }
            }
            composable(RoutesNavBottom.News) {
                NewsScreen(navController)
            }
            composable(RoutesNavBottom.Favorite) {
                FavoritesScreen(navController)
            }
            composable("${RoutesNavBottom.DetailsFavorite}/{newsId}") { backStackEntry ->
                val newsId = backStackEntry.arguments?.getString("newsId")?.toInt() ?: 0
                FavoritesDetailsScreen(navController, newsId)
            }
        }
    }
}
