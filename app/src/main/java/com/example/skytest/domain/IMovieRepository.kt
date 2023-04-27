package com.example.skytest.domain

import com.example.skytest.data.model.ApiMovieItem
import retrofit2.Response

interface IMovieRepository {
    suspend fun getMovies(): Response<List<ApiMovieItem>>
}
