package com.example.movies.details.ui

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movies.details.ui.components.HeaderDetails
import com.example.movies.details.ui.components.NewsContent
import com.example.movies.shared.data.di.RetrofitProvider

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsScreen(navController: NavController, id: Int) {
    val context = LocalContext.current
    val detailsViewModel: DetailsViewModel = viewModel(factory = DetailsViewModelFactory(
        RetrofitProvider.newsIdRepository,
        RetrofitProvider.databaseRepository
    ))

    LaunchedEffect(id) {
        detailsViewModel.getNewsById(id)
    }

    val news by detailsViewModel.newsById.collectAsState()
    val error by detailsViewModel.error.collectAsState()

    if (error != null) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        detailsViewModel.clearError()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Ensure `news` is not null before accessing it
        if (news != null) {
            HeaderDetails(navController, detailsViewModel, news)
            NewsContent(news)
        }
    }
}
