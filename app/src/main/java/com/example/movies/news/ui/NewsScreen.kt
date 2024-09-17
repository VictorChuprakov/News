package com.example.movies.news.ui

import NewsViewModel
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movies.news.ui.components.HeaderHome
import com.example.movies.news.ui.components.PagerCategory
import com.example.movies.shared.data.dataprefence.DataPreference
import com.example.movies.shared.data.di.RetrofitProvider


@Composable
fun NewsScreen(navController: NavController) {
    val context = LocalContext.current
    val dataPreference = remember { DataPreference(context) }
    val newsViewModel: NewsViewModel =
        viewModel(factory = NewsViewModelFactory(RetrofitProvider.newsRepository, dataPreference))

    val error by newsViewModel.newsError.collectAsState()

    LaunchedEffect(error) {
        error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            newsViewModel.clearError()
        }
    }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        HeaderHome()
        PagerCategory(
            navController = navController,
            newsViewModel = newsViewModel
        )
    }
}