package com.example.movies

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.movies.details.ui.DetailsViewModel
import com.example.movies.details.ui.DetailsViewModelFactory
import com.example.movies.shared.data.di.RetrofitProvider
import com.example.movies.shared.ui.bottomNavigation.NavHostContainer
import com.example.movies.ui.theme.MoviesTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RetrofitProvider.init(applicationContext)
        enableEdgeToEdge()
        setContent {
            MoviesTheme {

                val navController = rememberNavController()
                NavHostContainer(navController = navController)
            }
        }
    }
}