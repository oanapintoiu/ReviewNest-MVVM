package com.example.reviewnest_mvvm.network.dto

import com.google.gson.annotations.SerializedName

//For the details endpoint /movie/{movie_id}

data class MovieDetailsDTO(
    val id: Int,
    val title: String,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("release_date")
    val releaseYear: String?,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("genres")
    val genres: List<GenresDTO>,

    @SerializedName("runtime")
    val runtime: Int?,

    @SerializedName("overview")
    val overview: String?
)

data class GenresDTO(
    val id: Int,
    val name: String
)
