package com.example.movies.favourites.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movies.R
import com.example.movies.favourites.ui.components.HeaderFavorite
import com.example.movies.shared.data.di.RetrofitProvider
import com.example.movies.shared.until.Routes

@Composable
fun FavoritesScreen(navController: NavHostController) {
    val favoriteViewModel: FavoriteViewModel = viewModel(
        factory = FavoriteViewModelFactory(RetrofitProvider.databaseRepository)
    )
    val favorites by favoriteViewModel.news.collectAsState()
    Column(Modifier.fillMaxSize( )) {
        HeaderFavorite()
        LazyColumn {
            items(favorites) { favorite ->
                Card(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("${Routes.DetailsFavorite}/${favorite.id}")
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
                            AsyncImage(
                                model = favorite.image,
                                contentDescription = "news image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(10.dp)),
                                placeholder = painterResource(id = R.drawable.placeholder_image), // Заглушка при загрузке
                                error = painterResource(id = R.drawable.placeholder_image), // Изображение при ошибке
                            )
                        }
                        Text(
                            text = favorite.title,
                            color = MaterialTheme.colorScheme.primary,
                            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 17.sp),
                            modifier = Modifier.padding(start = 10.dp)
                        )

                    }
                }
            }
        }
    }

}

