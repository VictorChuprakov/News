package com.example.movies.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movies.R
import com.example.movies.home.data.News
import com.example.movies.shared.until.Routes

@Composable
fun CardNews(navController: NavController, news: News) {
    // Состояние для отслеживания загрузки
    var isLoading by remember { mutableStateOf(true) }

    // Локальная функция для обработки изменений состояния
    fun onLoadingStarted() {
        isLoading = true
    }

    fun onLoadingFinished() {
        isLoading = false
    }

    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate("${Routes.Details}/${news.id}")
            },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                // Проверка и отображение изображения
                AsyncImage(
                    model = news.image,
                    contentDescription = "news image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp)),
                    placeholder = painterResource(id = R.drawable.placeholder_image), // Заглушка при загрузке
                    error = painterResource(id = R.drawable.placeholder_image), // Изображение при ошибке
                    onLoading = {
                        onLoadingStarted()
                    },
                    onSuccess = {
                        onLoadingFinished()
                    },
                    onError = {
                        onLoadingFinished()
                    }
                )
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(Color.Black.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color.White)
                    }
                }
            }
            Text(
                text = news.title,
                color = MaterialTheme.colorScheme.primary,
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 17.sp),
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}
