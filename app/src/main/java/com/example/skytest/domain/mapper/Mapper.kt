package com.example.skytest.domain.mapper

import com.example.skytest.data.model.ApiMovieItem
import com.example.skytest.domain.model.Movie


fun List<ApiMovieItem>?.mapToDomain(): List<Movie> {
    val list = mutableListOf<Movie>()
    this?.map {
        list.add(
            Movie(
                title = it.Title.orEmpty(),
                genre = it.Genre.orEmpty(),
                imageUrl = it.Poster.orEmpty(),
            )
        )
    }
    return list
}

