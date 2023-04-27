package com.example.skytest.data.repository

import com.example.skytest.data.model.ApiMovieItem
import com.example.skytest.domain.IMovieRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieService,
    private val ioDispatcher: CoroutineDispatcher
) : IMovieRepository {
    override suspend fun getMovies(): Response<List<ApiMovieItem>> =
        withContext(ioDispatcher) { service.getMovies() }
}
