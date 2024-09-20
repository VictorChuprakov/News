package com.example.movies.details.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.common.data.api.ApiError
import com.example.movies.common.data.api.State
import com.example.movies.common.ui.components.ShowToast
import com.example.movies.details.data.model.NewsId
import com.example.movies.details.ui.components.Header
import com.example.movies.details.ui.components.HeaderDetails
import com.example.movies.details.ui.components.NewsContent

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsScreen(navController: NavController, id: Int) {
    val detailsViewModel: DetailsViewModel = hiltViewModel()
    val state by detailsViewModel.state.collectAsState()

    LaunchedEffect(id) {
        detailsViewModel.getNewsById(id)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is State.Loading -> {
                Header(navController)
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is State.Success -> {
                val news = (state as State.Success<NewsId>).data
                HeaderDetails(navController, detailsViewModel, news)
                NewsContent(news)
            }
            is State.Error -> {
                val error = (state as State.Error).error
                val errorMessage = when (error) {
                    ApiError.NETWORK_ERROR -> stringResource(id = R.string.network_error)
                    ApiError.RESPONSE_NULL ->  stringResource(id = R.string.responce_null)
                    ApiError.REQUEST_FAILED ->  stringResource(id = R.string.request_failed)
                    ApiError.UNEXPECTED_ERROR ->  stringResource(id = R.string.unexpected_error)
                }
                Header(navController = navController)
                ShowToast(errorMessage)
            }
        }
    }
}
