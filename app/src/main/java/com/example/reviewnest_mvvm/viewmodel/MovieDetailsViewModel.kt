package com.example.reviewnest_mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reviewnest_mvvm.model.MovieDetailsModel
import com.example.reviewnest_mvvm.network.api.TMDbApiProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel (
    private val provider: TMDbApiProvider
): ViewModel() {

    private val _movieDetails = MutableStateFlow<MovieDetailsModel?>(null)
    val details: StateFlow<MovieDetailsModel?> = _movieDetails

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            val detailsResult = provider.getMovieDetails(movieId)
            _movieDetails.value = detailsResult
        }
    }
}
