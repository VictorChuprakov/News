package com.example.movies.news.presentation.main.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movies.common.utils.getFormattedDate
import com.example.movies.navigation.Routes
import com.example.movies.news.data.main.model.NewsItem


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("ResourceType")
@Composable
fun CardNews(
    navController: NavController,
    news: NewsItem,
) {
    val date = getFormattedDate(news.createdAt)
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.large
            )
            .clickable(onClick = { navController.navigate(Routes.Details.route + "/${news.id}") } ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .height(130.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(news.image)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(130.dp)
                    .clip(MaterialTheme.shapes.large)
            )
            Spacer(modifier = Modifier.width(10.dp))

            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = news.title,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.primary,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    color = MaterialTheme.colorScheme.primaryContainer,
                )
            }
        }
    }
}
