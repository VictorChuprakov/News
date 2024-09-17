package com.example.movies.details.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.details.data.model.NewsId
import com.example.movies.details.ui.DetailsViewModel
import com.example.movies.shared.until.Routes

@Composable
fun HeaderDetails(navController: NavController, detailsViewModel: DetailsViewModel, news: NewsId) {
    val isFavorite by detailsViewModel.isFavorite.collectAsState()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.navigate(Routes.News) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = colorResource(id = R.color.FoxBlue))) {
                        append("Fox")
                    }
                    withStyle(style = SpanStyle(color = colorResource(id = R.color.FoxRed))) {
                        append("News")
                    }
                },
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        IconButton(onClick = { detailsViewModel.toggleFavorite(news) }) {
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                contentDescription = "BookMark",
                tint = colorResource(id = R.color.FoxBlue)
            )
        }
    }
}
