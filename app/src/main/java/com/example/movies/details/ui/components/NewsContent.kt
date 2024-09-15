package com.example.movies.details.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movies.R
import com.example.movies.details.data.model.NewsId
import com.example.movies.details.ui.DetailsViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsContent(news: NewsId?) {
    var isLoading by remember {
        mutableStateOf(false)
    }
    fun onLoadingStarted() {
        isLoading = true
    }
    fun onLoadingFinished() {
        isLoading = false
    }
    news?.let {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Text(
                    text = it.title,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .basicMarquee()
                )
                AsyncImage(
                    model = it.image,
                    contentDescription = "news image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(16/9f)
                        .clip(RoundedCornerShape(10.dp)),
                    placeholder = painterResource(id = R.drawable.placeholder_image),
                    error = painterResource(id = R.drawable.placeholder_image),
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
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = it.content,
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}