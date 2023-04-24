package com.example.skytest.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import com.example.skytest.R
import com.example.skytest.presentation.HomeState
import com.example.skytest.ui.components.state.ManageResourceState

@Composable
fun MovieGrid(uiState: State<HomeState>) {
    Scaffold {
        ManageResourceState(
            resourceState = uiState.value.movies,
            onTryAgain = {
            // TODO: implement try again event
            },
            successView = { list ->
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = dimensionResource(id = R.dimen.movie_grid_item_width)),
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.testTag("lazyGrid")
                ) {
                    list?.size?.let { size ->
                        items(size) { index ->
                            MovieView(movie = list[index])
                        }
                    }
                }
            }
        )
    }
}
