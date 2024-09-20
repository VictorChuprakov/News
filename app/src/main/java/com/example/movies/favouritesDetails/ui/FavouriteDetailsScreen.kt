package com.example.movies.favourites.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.movies.favouritesDetails.ui.FavoriteDetailsViewModel
import com.example.movies.favouritesDetails.ui.components.HeaderDetailsFavorite
import com.example.movies.favouritesDetails.ui.components.NewsFavoritesDetailsContent

@Composable
fun FavoritesDetailsScreen(navController: NavHostController, id: Int) {
    val favoriteDetailsViewModel: FavoriteDetailsViewModel = hiltViewModel()

    LaunchedEffect(id) {
        favoriteDetailsViewModel.getNewsDetailsById(id)
    }

    val favoriteNews by favoriteDetailsViewModel.newsDetails.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        favoriteNews?.let { news ->
            HeaderDetailsFavorite(
                navController = navController,
                favoriteDetailsViewModel,
                news.id
            )
            NewsFavoritesDetailsContent(news)
        }
    }
}

