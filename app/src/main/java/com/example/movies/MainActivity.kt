package com.example.movies

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.movies.shared.data.dataprefence.DataPreference
import com.example.movies.shared.ui.bottomNavigation.NavHostContainer
import com.example.movies.ui.theme.MoviesTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesTheme {
                val context = LocalContext.current.applicationContext
                val dataPreference = DataPreference(context)
                val navController = rememberNavController()
                NavHostContainer(navController = navController, dataPreference)
            }
        }
    }
}