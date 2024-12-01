package com.example.movies.navigation


const val ROOT_GRAPH_ROUTE = "root"
const val AUTH_GRAPH_ROUTE = "auth"
const val HOME_GRAPH_ROUTE = "home"

sealed class Routes (val route: String){
    data object Home: Routes("home_screen")
    data object Favorite: Routes("favorite_screen")
    data object Profile: Routes("profile_screen")
    data object Details: Routes("details_screen")
    data object TermsAndCondition: Routes("terms_and_condition_screen")
    data object ChangePassword: Routes("change_password_screen")
    data object Privacy: Routes("privacy_screen")
    data object FavoriteDetails: Routes("favorite_details_screen")
    data object Main: Routes("main_screen")
    data object Login: Routes("login_screen")
}