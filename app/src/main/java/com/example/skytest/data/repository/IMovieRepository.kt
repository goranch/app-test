package com.example.skytest.data.repository

import com.example.skytest.data.model.ApiMovieItem
import retrofit2.Response

interface IMovieRepository {
    suspend fun getMovies(): Response<List<ApiMovieItem>>
}
