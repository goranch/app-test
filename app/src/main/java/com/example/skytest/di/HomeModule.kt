package com.example.skytest.di

import com.example.skytest.data.repository.MovieRepositoryImpl
import com.example.skytest.data.repository.MovieService
import com.example.skytest.data.repository.IMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

const val IO_DISPATCHER = "IO"

@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

    @Provides
    @ViewModelScoped
    @Named(IO_DISPATCHER)
    fun provideIODispatchers(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @ViewModelScoped
    fun provideMovieRepository(
        service: MovieService,
        @Named(IO_DISPATCHER) coroutineDispatcher: CoroutineDispatcher
    ): IMovieRepository {
        return MovieRepositoryImpl(service, coroutineDispatcher)
    }
}
