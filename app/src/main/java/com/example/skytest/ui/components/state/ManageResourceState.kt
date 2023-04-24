package com.example.skytest.ui.components.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.skytest.base.BaseUiState

@Composable
fun <T> ManageResourceState(
    resourceState: BaseUiState<T>,
    successView: @Composable (data: T?) -> Unit,
    onTryAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = modifier.fillMaxSize()
    ) {
        when (resourceState) {
            is BaseUiState.Success -> successView(resourceState.data)
            is BaseUiState.Error -> resourceState.message?.let { Error(onTryAgain = onTryAgain, msg = it) }
            is BaseUiState.Loading -> Loading()
            is BaseUiState.Empty -> TODO()
        }
    }
}
