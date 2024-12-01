package com.example.movies.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

fun NavGraphBuilder.authNavGraph(
){
    navigation(
        startDestination = Routes.Login.route,
        route = AUTH_GRAPH_ROUTE
    ){

    }
}