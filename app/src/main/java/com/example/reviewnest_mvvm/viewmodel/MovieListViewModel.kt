package com.example.reviewnest_mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reviewnest_mvvm.model.MovieListItemModel
import com.example.reviewnest_mvvm.network.api.TMDbApiProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val provider: TMDbApiProvider
) : ViewModel() {

    private val _movies = MutableStateFlow<List<MovieListItemModel>>(emptyList())
    val movies: StateFlow<List<MovieListItemModel>> = _movies

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            val moviesList = provider.getMovies()
            _movies.value = moviesList
        }
    }
}
