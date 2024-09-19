package com.example.movies.common.ui.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShowToast(message: String) {
    val context = LocalContext.current.applicationContext
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}