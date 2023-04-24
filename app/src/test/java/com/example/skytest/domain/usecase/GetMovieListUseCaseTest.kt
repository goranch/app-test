package com.example.skytest.domain.usecase

import com.example.skytest.data.model.ApiMovieItem
import com.example.skytest.data.model.Rating
import com.example.skytest.domain.mapper.mapToDomain
import com.example.skytest.domain.model.Movie
import org.junit.Assert.assertEquals
import org.junit.Test

class GetMovieListUseCaseTest {

    @Test
    fun `Test mapping to domain when all data is present`() {
        assertEquals(listOfMoviesFormApi().mapToDomain(), listOfExpectedMovies())
    }

    @Test
    fun `Test mapping to domain when all data is null`() {
        assertEquals(listOfNullMoviesFormApi().mapToDomain(), listOfNullExpectedMovies())
    }

    @Test
    fun `Test when the list to be mapped is null`() {
        assertEquals(nullList().mapToDomain(), listOf<Movie>())
    }
}

private fun listOfExpectedMovies(): List<Movie> {
    return listOf(
        Movie(title = "title", genre = "genre", imageUrl = "poster"),
    )
}

private fun listOfNullExpectedMovies(): List<Movie> {
    return listOf(
        Movie(title = "", genre = "", imageUrl = "")
    )
}

private fun nullList(): List<ApiMovieItem>? {
    return null
}

private fun listOfMoviesFormApi(): List<ApiMovieItem> {
    return listOf(
        ApiMovieItem(
            Actors = "actors",
            Awards = "awards",
            BoxOffice = "box",
            Country = "UK",
            DVD = "dvd",
            Director = "director",
            Genre = "genre",
            Language = "english",
            Metascore = 2,
            Plot = "plot",
            Poster = "poster",
            Production = "prod",
            Rated = "rated",
            Ratings = listOf(Rating(Source = "s", Value = "v")),
            Released = "re",
            Response = "res",
            Runtime = "r",
            Title = "title",
            Type = "type",
            Website = "sad",
            Writer = "sd",
            Year = 2000,
            imdbID = "5",
            imdbRating = 5.5,
            imdbVotes = "sd"
        )
    )
}

private fun listOfNullMoviesFormApi(): List<ApiMovieItem> {
    return listOf(
        ApiMovieItem(
            Actors = null,
            Awards = null,
            BoxOffice = null,
            Country = null,
            DVD = null,
            Director = null,
            Genre = null,
            Language = null,
            Metascore = null,
            Plot = null,
            Poster = null,
            Production = null,
            Rated = null,
            Ratings = null,
            Released = null,
            Response = null,
            Runtime = null,
            Title = null,
            Type = null,
            Website = null,
            Writer = null,
            Year = null,
            imdbID = null,
            imdbRating = null,
            imdbVotes = null
        )
    )
}
