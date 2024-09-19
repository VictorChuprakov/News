package com.example.movies.favourites.ui

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movies.R
import com.example.movies.common.data.room.DatabaseProvider
import com.example.movies.favouritesDetails.ui.FavoriteDetailsViewModel
import com.example.movies.favouritesDetails.ui.components.HeaderDetailsFavorite
import com.example.movies.favouritesDetails.ui.components.NewsFavoritesDetailsContent

@Composable
fun FavoritesDetailsScreen(navController: NavHostController, id: Int) {
    val favoriteDetailsViewModel: FavoriteDetailsViewModel = viewModel(
        factory = FavoriteDetailsViewModelFactory(DatabaseProvider.databaseRepository)
    )

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

