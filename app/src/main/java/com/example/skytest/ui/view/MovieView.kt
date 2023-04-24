package com.example.skytest.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.skytest.R
import com.example.skytest.domain.model.Movie


@Composable
fun MovieView(movie: Movie) {
    Box(
        Modifier
            .padding(dimensionResource(id = R.dimen.medium_padding))
    ) {
        AsyncImage(
            model = movie.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Text(
            text = movie.genre,
            color = Color.White,
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.5f))
                .align(Alignment.TopEnd)
                .padding(dimensionResource(id = R.dimen.medium_padding))
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
    val movie = Movie(
        title = "The Irishman",
        genre = "Crime",
        imageUrl = "https://www.example.com/the-irishman.jpg"
    )
    MovieView(movie = movie)
}
