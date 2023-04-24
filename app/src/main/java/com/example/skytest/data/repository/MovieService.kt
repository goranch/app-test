package com.example.skytest.data.repository

import com.example.skytest.data.model.ApiMovieItem
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {
    @GET("759fdfa82d6f33522e11")
    suspend fun getMovies(): Response<List<ApiMovieItem>>
}
