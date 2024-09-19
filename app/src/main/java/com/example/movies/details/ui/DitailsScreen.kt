package com.example.movies.details.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.common.data.api.ApiError
import com.example.movies.common.data.api.RetrofitProvider
import com.example.movies.common.data.api.State
import com.example.movies.common.data.room.DatabaseProvider
import com.example.movies.common.ui.ShowToast
import com.example.movies.details.data.model.NewsId
import com.example.movies.details.ui.components.HeaderDetails
import com.example.movies.details.ui.components.HeaderError
import com.example.movies.details.ui.components.NewsContent

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsScreen(navController: NavController, id: Int) {
    val detailsViewModel: DetailsViewModel = viewModel(
        factory = DetailsViewModelFactory(
            RetrofitProvider.newsIdRepository,
            DatabaseProvider.databaseRepository
        )
    )
    val state by detailsViewModel.state.collectAsState()

    LaunchedEffect(id) {
        detailsViewModel.getNewsById(id)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is State.Loading -> {
                HeaderError(navController)
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is State.Success -> {
                val news = (state as State.Success<NewsId>).data
                if (news != null) {
                    HeaderDetails(navController, detailsViewModel, news)
                    NewsContent(news)
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(stringResource(id = R.string.no_news))
                    }
                }
            }
            is State.Error -> {
                val error = (state as State.Error).error
                val errorMessage = when (error) {
                    ApiError.NETWORK_ERROR -> "Network error occurred"
                    ApiError.RESPONSE_NULL -> "Response body is null"
                    ApiError.REQUEST_FAILED -> "Request failed"
                    ApiError.UNEXPECTED_ERROR -> "An unexpected error occurred"
                }
                HeaderError(navController)
                ShowToast(errorMessage)
            }
        }
    }
}
