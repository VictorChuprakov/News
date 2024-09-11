package com.example.movies.shared.ui.bottomNavigation

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
import com.example.movies.favourites.ui.FavouriteScreen
import com.example.movies.home.ui.HomeScreen
import com.example.movies.search.ui.SearchScreen
import com.example.movies.shared.data.dataprefence.DataPreference
import com.example.movies.shared.until.Routes

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavHostContainer(navController: NavHostController, dataPreference: DataPreference) {
    Scaffold(
        bottomBar = {
            if (navController.currentBackStackEntryAsState().value?.destination?.route != "${Routes.Details}/{id}") {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Home,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                route = "${Routes.Details}/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id")
                id?.let { DetailsScreen(navController, it) }
            }
            composable(Routes.Home) {
                HomeScreen(navController, dataPreference)
            }
            composable(Routes.Search) {
                SearchScreen()
            }
            composable(Routes.Favorite) {
                FavouriteScreen()
            }
        }
    }
}
