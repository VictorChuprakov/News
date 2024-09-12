package com.example.movies.news.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
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
    val newsViewModel: NewsViewModel = viewModel(factory = NewsViewModelFactory(RetrofitProvider.newsRepository, dataPreference))
    val error by newsViewModel.newsError.collectAsState()
    Column(modifier = Modifier.statusBarsPadding()) {
        if (error != null) {
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
        HeaderHome()
        PagerCategory(
            navController = navController,
            newsViewModel = newsViewModel
        )
    }
}
