package com.example.movies.news.presentation.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movies.common.data.api.State
import com.example.movies.common.data.room.entity.FavoriteNewsEntity
import com.example.movies.common.presentation.components.ErrorDisplay
import com.example.movies.common.presentation.components.LoadingIndicator
import com.example.movies.news.data.details.model.NewsId
import com.example.movies.news.presentation.details.components.DetailsContent

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsDetailsScreen(navController: NavController, id: Int) {
    val detailsViewModel: NewsDetailsViewModel =
        hiltViewModel<NewsDetailsViewModel, NewsDetailsViewModel.DetailsViewModelFactory> { detailsViewModelFactory ->
            detailsViewModelFactory.create(id)
        }
    val state by detailsViewModel.state.collectAsState()
    val scaffoldSheetState = rememberBottomSheetScaffoldState()
    val isFavorite by detailsViewModel.isFavorite.collectAsState()

    when (state) {
        is State.Loading -> LoadingIndicator()
        is State.Success -> {
            val news = (state as State.Success<NewsId>).data
            DetailsContent(
                news, navController, scaffoldSheetState, onClickSaveNews = {
                    detailsViewModel.toggleFavorite(
                        FavoriteNewsEntity(
                            createdAt = news.createdAt,
                            content = news.content,
                            apiId = news.id,
                            image = news.image,
                            title = news.title,
                        )
                    )
                },
                isFavorite
            )
        }

        is State.Error -> {
            val error = (state as State.Error).throwable
            error.localizedMessage?.let { ErrorDisplay(it, onRetryClick = {}) }
        }
        else -> {}
    }
}




