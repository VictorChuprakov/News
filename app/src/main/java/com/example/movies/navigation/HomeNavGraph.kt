package com.example.movies.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.movies.common.presentation.MainScreen

@RequiresApi(Build.VERSION_CODES.O)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
fun NavGraphBuilder.homeNavGraph(){
    navigation(
        startDestination = Routes.Main.route,
        route = HOME_GRAPH_ROUTE,
    ) {
        composable(
            route = Routes.Main.route
        ) {
            MainScreen()
        }
    }
}