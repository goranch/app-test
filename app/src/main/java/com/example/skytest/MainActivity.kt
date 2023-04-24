package com.example.skytest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.skytest.base.BaseUiState
import com.example.skytest.domain.model.Movie
import com.example.skytest.presentation.HomeState
import com.example.skytest.presentation.HomeViewModel
import com.example.skytest.ui.theme.SkyTestTheme
import com.example.skytest.ui.view.MovieGrid
import com.example.skytest.ui.view.SearchBox
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.flow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkyTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val state = viewModel.uiState.collectAsStateWithLifecycle()

    Column {
        SearchBox(onSearch = { viewModel.onSearch(it) })
        MovieGrid(uiState = state)
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val homeState = HomeState(
        movies = BaseUiState.Success(
            listOf(
                Movie(title = "title", genre = "Crime", imageUrl = "url"),
                Movie(title = "title 2", genre = "Comedy", imageUrl = "url"),
                Movie(title = "title 3", genre = "Drama", imageUrl = "url"),
            )
        )
    )
    val state = flow { emit(homeState) }.collectAsStateWithLifecycle(initialValue = homeState)

    SkyTestTheme {
        MovieGrid(state)
    }
}
