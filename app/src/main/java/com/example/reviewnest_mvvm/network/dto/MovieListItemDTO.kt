package com.example.reviewnest_mvvm.network.dto

import com.google.gson.annotations.SerializedName

//For endpoints like /movie/popular, /movie/top_rated, etc.

data class MoviesListResponse(
    val results: List<MoviesListItemDTO>
)

data class MoviesListItemDTO(
    val id: Int,
    val title: String,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("release_date")
    val releaseYear: String?
)
