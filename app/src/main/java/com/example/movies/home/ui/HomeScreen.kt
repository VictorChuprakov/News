package com.example.movies.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movies.home.data.repository.GetNewsRepositoryImpl
import com.example.movies.home.ui.components.HeaderHome
import com.example.movies.home.ui.components.PagerCategory
import com.example.movies.shared.data.api.RetrofitClient
import com.example.movies.shared.data.dataprefence.DataPreference

@Composable
fun HomeScreen(navController: NavController, dataPreference: DataPreference) {
    val repository = GetNewsRepositoryImpl(RetrofitClient.apiNews)
    val newsViewModel: NewsViewModel = viewModel(factory = NewsViewModelFactory(repository, dataPreference))
    Column {
        HeaderHome(navController = navController)
        PagerCategory(
            navController = navController,
            newsViewModel
        )
    }
}
