package com.example.reviewnest_mvvm.mappers

import com.example.reviewnest_mvvm.network.dto.MoviesListItemDTO
import com.example.reviewnest_mvvm.model.MovieListItemModel

fun MoviesListItemDTO.toMoviesListItemModel(): MovieListItemModel =
    MovieListItemModel(
    id = id.toString(),
    title = title,
    landscapeMoviePoster = backdropPath?.let { "https://image.tmdb.org/t/p/w780$it" } ?: "",
    year = releaseYear?.take(4) ?: "",
)

fun List<MoviesListItemDTO>.toMoviesModelList(): List<MovieListItemModel> = map { it.toMoviesListItemModel() }

//toMovieListItemModel() - for one movie
//toMovieListItemModelList() - for the movie list screen
