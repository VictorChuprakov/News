package com.example.movies.news.presentation.details.components

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movies.R
import com.example.movies.common.presentation.details.BackButton
import com.example.movies.news.data.details.model.NewsId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsContent(
    news: NewsId,
    navController: NavController,
    scaffoldSheetState: BottomSheetScaffoldState,
    onClickSaveNews: () -> Unit,
    isFavorite: Boolean
) {
    val context = LocalContext.current
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val sheetHeightPx = remember { (screenHeight * 0.65f).value.dp }

    Box(modifier = Modifier.fillMaxSize()) {
        BottomSheetScaffold(
            sheetPeekHeight = sheetHeightPx,
            scaffoldState = scaffoldSheetState,
            sheetContainerColor = MaterialTheme.colorScheme.background,
            sheetDragHandle = {
                BottomSheetDefaults.DragHandle(
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.medium,
                    width = 48.dp,
                    height = 4.dp
                )
            },
            sheetContent = { NewsDetailsContent(news) },
            content = {
                ImageHeader(news, context)
                BackButton(navController)
            }
        )
        FloatingActionButton(
            onClick = onClickSaveNews,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(30.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(if (isFavorite) R.drawable.ic_favorite_fill else R.drawable.ic_favorite_border),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun ImageHeader(news: NewsId, context: Context) {
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(news.image)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
private fun NewsDetailsContent(news: NewsId) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(horizontal = 20.dp)
    ) {
        item {
            Text(
                text = news.content,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

