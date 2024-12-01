package com.example.movies.common.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movies.profile.presentation.change_password.ChangePasswordScreen
import com.example.movies.common.presentation.bottomNavigation.BottomBar
import com.example.movies.navigation.Routes
import com.example.movies.favorite.presentation.main.FavoritesScreen
import com.example.movies.favorite.presentation.details.FavoriteDetailsScreen
import com.example.movies.news.presentation.main.HomeScreen
import com.example.movies.news.presentation.details.NewsDetailsScreen
import com.example.movies.profile.presentation.privacy.PrivacyScreen
import com.example.movies.profile.presentation.profile.ProfileScreen
import com.example.movies.profile.presentation.terms.TermsAndConditionsScreen

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    Scaffold(
        floatingActionButton = {
            if (currentRoute != "${Routes.Details.route}/{id}" && currentRoute != Routes.TermsAndCondition.route && currentRoute != Routes.Privacy.route
                && currentRoute != Routes.ChangePassword.route && currentRoute != "${Routes.FavoriteDetails.route}/{id}" ) {
                BottomBar(navController)
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Home.route,
            modifier = Modifier.let {
                if (currentRoute == "${Routes.Details.route}/{id}" || currentRoute == "${Routes.FavoriteDetails.route}/{id}"
                    ||  currentRoute == Routes.Home.route ||  currentRoute == Routes.Favorite.route
                    ) {
                    it
                } else
                    it.padding(innerPadding)
            }
        ) {
            composable(
                route = "${Routes.Details.route}/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val newsId = backStackEntry.arguments?.getInt("id")
                newsId?.let { NewsDetailsScreen(navController,newsId) }
            }
            composable(Routes.Home.route) {
                HomeScreen(navController)
            }
            composable(Routes.Favorite.route) {
                FavoritesScreen(navController)
            }
            composable(Routes.Profile.route) {
                ProfileScreen(navController)
            }
            composable(Routes.TermsAndCondition.route){
                TermsAndConditionsScreen(onClick = {navController.popBackStack()})
            }
            composable(Routes.ChangePassword.route){
                ChangePasswordScreen(onClick = {navController.popBackStack()})
            }
            composable(Routes.Privacy.route){
                PrivacyScreen(onClick = {navController.popBackStack()})
            }
            composable(Routes.Privacy.route){
                PrivacyScreen(onClick = {navController.popBackStack()})
            }
            composable(
                route = "${Routes.FavoriteDetails.route}/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val newsId = backStackEntry.arguments?.getInt("id")
                newsId?.let { FavoriteDetailsScreen(navController,newsId) }
            }
        }
    }
}






