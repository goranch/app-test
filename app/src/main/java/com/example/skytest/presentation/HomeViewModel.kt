package com.example.skytest.presentation

import androidx.lifecycle.viewModelScope
import com.example.skytest.base.BaseUiState
import com.example.skytest.base.BaseViewModel
import com.example.skytest.base.UiState
import com.example.skytest.domain.usecase.GetMovieListUseCase
import com.example.skytest.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

data class HomeState(
    val movies: BaseUiState<List<Movie>>
) : UiState

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase
) : BaseViewModel<HomeState>() {
    private var movieList: List<Movie> = mutableListOf()

    init {
        fetchMovies()
    }

    override fun createInitialState(): HomeState {
        // should load cached data here
        return HomeState(movies = BaseUiState.Empty)
    }

    private fun fetchMovies() {
        setState { copy(movies = BaseUiState.Loading) }

        viewModelScope.launch {
            getMovieListUseCase().collect { response ->
                response.onSuccess {
                    movieList = it
                    setState { copy(movies = BaseUiState.Success(it)) }
                }.onFailure {
                    setState { copy(movies = BaseUiState.Error(it.message)) }
                }
            }
        }
    }

    private fun filterMovies(filterString: String, movies: List<Movie>): List<Movie> {
        return movies.filter {
            it.title.lowercase().contains(filterString) || it.genre.lowercase().contains(filterString)
        }
    }

    fun onSearch(filterString: String) {

        val filtered = filterMovies(filterString, movieList)

        setState { copy(movies = BaseUiState.Success(filtered)) }
    }

}
