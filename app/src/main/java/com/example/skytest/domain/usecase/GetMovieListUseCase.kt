package com.example.skytest.domain.usecase

import com.example.skytest.domain.IMovieRepository
import com.example.skytest.domain.model.Movie
import com.example.skytest.domain.mapper.mapToDomain
import com.example.skytest.domain.usecase.type.UseCaseOut
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val repository: IMovieRepository
) : UseCaseOut<List<Movie>>() {

    override val block: suspend () -> List<Movie> = {

        repository.getMovies().body().mapToDomain()
    }
}

