package com.example.movies.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.shared.until.Routes


@Composable
fun HeaderHome(navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
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
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        IconButton(onClick = { navController.navigate(Routes.Search) }) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "search",
            tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}