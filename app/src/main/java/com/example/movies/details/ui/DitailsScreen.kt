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
import com.example.movies.shared.data.di.RetrofitProvider
import com.example.movies.details.ui.components.HeaderDetails
import com.example.movies.details.ui.components.NewsContent

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsScreen(navController: NavController, id: Int) {
    val detailsViewModel: DetailsViewModel = viewModel(factory = DetailsViewModelFactory(RetrofitProvider.newsIdRepository))

    LaunchedEffect(id) {
        detailsViewModel.getNewsById(id)
    }

    val news by detailsViewModel.newsById.collectAsState()
    val error by detailsViewModel.error.collectAsState()
    if(error != null){
        Toast.makeText(LocalContext.current, error, Toast.LENGTH_SHORT).show()
        detailsViewModel.clearError()
    }
    Column(modifier = Modifier.fillMaxSize()) {
        HeaderDetails(navController = navController)
        NewsContent(news)
    }
}
