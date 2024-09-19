package com.example.movies.favourites.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movies.common.data.room.DatabaseProvider
import com.example.movies.common.ui.components.HeaderDefault
import com.example.movies.favourites.components.NewsFavoritesScreen

@Composable
fun FavoritesScreen(navController: NavController) {
    val favoriteViewModel: FavoriteViewModel = viewModel(
        factory = FavoriteViewModelFactory(DatabaseProvider.databaseRepository)
    )
    val favorites by favoriteViewModel.news.collectAsState()
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        HeaderDefault()
        Spacer(modifier = Modifier.height(20.dp))
        NewsFavoritesScreen(navController = navController,favorites)

    }
}

