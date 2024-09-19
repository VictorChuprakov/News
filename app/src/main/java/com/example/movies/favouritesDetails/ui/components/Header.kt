package com.example.movies.favouritesDetails.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.favouritesDetails.ui.FavoriteDetailsViewModel
import com.example.movies.common.ui.bottomNavigation.RoutesNavBottom

@Composable
fun HeaderDetailsFavorite(
    navController: NavController,
    favoriteDetailsViewModel: FavoriteDetailsViewModel,
    id: Int,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.navigate(RoutesNavBottom.Favorite) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                )
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = colorResource(id = R.color.FoxBlue))) {
                        append(stringResource(id = R.string.title_Fox))
                    }
                    withStyle(style = SpanStyle(color = colorResource(id = R.color.FoxRed))) {
                        append(stringResource(id = R.string.title_News))
                    }
                },
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        IconButton(onClick = {
            favoriteDetailsViewModel.deleteDetails(id)
            navController.navigate(RoutesNavBottom.Favorite)
        }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = colorResource(id = R.color.FoxBlue)
            )
        }
    }
}


