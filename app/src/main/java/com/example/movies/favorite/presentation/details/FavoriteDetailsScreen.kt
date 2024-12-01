package com.example.movies.favorite.presentation.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movies.common.data.room.entity.FavoriteNewsEntity
import com.example.movies.favorite.presentation.details.components.FavoriteDetailsContent

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FavoriteDetailsScreen(navController: NavController, id: Int) {
    val detailsViewModel: FavoriteDetailsViewModel =
        hiltViewModel<FavoriteDetailsViewModel, FavoriteDetailsViewModel.FavoriteDetailsViewModelFactory> { detailsViewModelFactory ->
            detailsViewModelFactory.create(id)
        }
    val favoriteNews by detailsViewModel.state.collectAsState()
    val scaffoldSheetState = rememberBottomSheetScaffoldState()
    val isFavorite by detailsViewModel.isFavorite.collectAsState()


    FavoriteDetailsContent(
        favoriteNews, navController, scaffoldSheetState, onClickSaveNews = {
            favoriteNews?.let {
                FavoriteNewsEntity(
                    createdAt = it.createdAt,
                    content = it.content,
                    apiId = it.apiId,
                    image = it.image,
                    title = it.title,
                )
            }?.let {
                detailsViewModel.toggleFavorite(
                    it
                )
            }
        },
        isFavorite
    )
}

