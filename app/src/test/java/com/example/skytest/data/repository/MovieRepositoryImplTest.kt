package com.example.skytest.data.repository

import com.example.skytest.MainCoroutineRule
import com.example.skytest.data.model.ApiMovieItem
import com.example.skytest.domain.IMovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import retrofit2.Response

@ExperimentalCoroutinesApi
class MovieRepositoryImplTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    val service = mockk<MovieService>()
    private val mockMovieItems = mockk<List<ApiMovieItem>>()

    private lateinit var cut: IMovieRepository


    @Before
    fun setUp() {
        cut = MovieRepositoryImpl(service, mainCoroutineRule.testDispatcher)
    }

    @Test
    fun `Test getMovies will call the service`() = runTest {
        // Given
        coEvery { service.getMovies() } returns Response.success(mockMovieItems)

        // When
        cut.getMovies()

        // Then
        coVerify {
            service.getMovies()
        }
    }
}
