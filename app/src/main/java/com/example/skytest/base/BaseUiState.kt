package com.example.skytest.base

sealed interface BaseUiState<out T> {

    data class Success<T>(val data: T) : BaseUiState<T>

    data class Error(val message: String? = null) : BaseUiState<Nothing>

    object Loading : BaseUiState<Nothing>

    object Empty : BaseUiState<Nothing>
}
