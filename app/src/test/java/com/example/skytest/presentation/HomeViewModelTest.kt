package com.example.skytest.presentation

import com.example.skytest.MainCoroutineRule
import com.example.skytest.base.BaseUiState
import com.example.skytest.domain.model.Movie
import com.example.skytest.domain.usecase.GetMovieListUseCase
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    val getMovieListUseCase = mockk<GetMovieListUseCase>()

    private lateinit var cut: HomeViewModel

    @Test
    fun `Test fetchMovies Loading state`() = runTest {
        // Given
        val expectedState = HomeState(movies = BaseUiState.Loading)

        // When
        cut = HomeViewModel(getMovieListUseCase)

        // Then
        assertEquals(expectedState, cut.uiState.value)
    }

    @Test
    fun `Test fetchMovies Success state`() = runTest {
        // Given
        val expectedState = HomeState(movies = BaseUiState.Success(getMovieList()))

        // When
        every { getMovieListUseCase() } returns flowOf(Result.success(getMovieList()))
        cut = HomeViewModel(getMovieListUseCase)

        // Then
        assertEquals(expectedState, cut.uiState.value)
    }

    @Test
    fun `Test fetchMovies Error state`() = runTest {
        // Given
        val expectedState = HomeState(movies = BaseUiState.Error("Error message"))

        // When
        every { getMovieListUseCase() } returns flowOf(Result.failure(Throwable("Error message")))
        cut = HomeViewModel(getMovieListUseCase)

        // Then
        assertEquals(expectedState, cut.uiState.value)
    }

    @Test
    fun `Test onSearch with empty filter string`() = runTest {
        // Given
        val expectedState = HomeState(movies = BaseUiState.Success(getMovieList()))

        // When
        every { getMovieListUseCase() } returns flowOf(Result.success(getMovieList()))
        cut = HomeViewModel(getMovieListUseCase)
        cut.onSearch("")

        // Then
        assertEquals(expectedState, cut.uiState.value)
    }

    @Test
    fun `Test onSearch filter string not found`() {
        // Given
        val expectedState = HomeState(movies = BaseUiState.Success(emptyList()))

        // When
        every { getMovieListUseCase() } returns flowOf(Result.success(getMovieList()))
        cut = HomeViewModel(getMovieListUseCase)

        cut.onSearch("Iron")

        // Then
        assertEquals(expectedState, cut.uiState.value)
    }

    @Test
    fun `Test onSearch by Title is successful and supports lowercase`() {
        // Given
        val expectedState = HomeState(movies = BaseUiState.Success(
                listOf(
                    Movie(title = "Knives Out", genre = "Comedy, Crime, Drama")
                )
            )
        )

        // When
        every { getMovieListUseCase() } returns flowOf(Result.success(getMovieList()))
        cut = HomeViewModel(getMovieListUseCase)

        cut.onSearch("out")

        // Then
        assertEquals(expectedState, cut.uiState.value)
    }

    @Test
    fun `Test onSearch by Genre is successful and supports lowercase`() {
        // Given
        every { getMovieListUseCase() } returns flowOf(Result.success(getMovieList()))
        val expectedState = HomeState(movies = BaseUiState.Success(
                listOf(
                    Movie(title = "The Irishman", genre = "Biography, Crime, Drama")
                )
            )
        )

        // When
        every { getMovieListUseCase() } returns flowOf(Result.success(getMovieList()))
        cut = HomeViewModel(getMovieListUseCase)

        cut.onSearch("bio")

        // Then
        assertEquals(expectedState, cut.uiState.value)
    }

    private fun getMovieList(): List<Movie> {
        return listOf(
            Movie(title = "Knives Out", genre = "Comedy, Crime, Drama"),
            Movie(title = "The Irishman", genre = "Biography, Crime, Drama")
        )
    }
}
