package com.example.reviewnest_mvvm.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.reviewnest_mvvm.BuildConfig
import com.example.reviewnest_mvvm.network.api.TMDbApiProvider
import com.example.reviewnest_mvvm.viewmodel.MovieDetailsViewModel
import com.example.reviewnest_mvvm.viewmodel.MovieListViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Retrieve the key from BuildConfig
            val apiKey = BuildConfig.TMDB_API_KEY

            // Pass it to the provider
            val provider = remember { TMDbApiProvider(apiKey) }
            var selectedMovieId by remember { mutableStateOf<Int?>(null) }

            if (selectedMovieId == null) {
                val viewModel = remember { MovieListViewModel(provider) }
                MovieListScreen(
                    viewModel = viewModel,
                    onMovieClicked = { movieId -> selectedMovieId = movieId }
                )
            } else {
                selectedMovieId?.let { movieId ->
                    val viewModel = remember { MovieDetailsViewModel(provider) }
                    MovieDetailsScreen(
                        viewModel = viewModel,
                        movieId = movieId,
                        onBack = { selectedMovieId = null }
                    )
                }
            }
        }
    }
}
