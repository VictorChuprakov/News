package com.example.movies.favourites.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movies.R

@Composable
fun HeaderFavorite() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .systemBarsPadding()
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = colorResource(id = R.color.FoxBlue))) {
                    append("Fox")
                }
                withStyle(style = SpanStyle(color = colorResource(id = R.color.FoxRed))) {
                    append("News")
                }
            },
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

