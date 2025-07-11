package com.example.reviewnest_mvvm.network.api

import com.example.reviewnest_mvvm.network.dto.CastCreditsDTO
import com.example.reviewnest_mvvm.network.dto.MovieDetailsDTO
import com.example.reviewnest_mvvm.network.dto.MoviesListResponse
import com.example.reviewnest_mvvm.network.dto.ReviewsResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDbApi {
    @GET("movie/top_rated")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): MoviesListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
    ): MovieDetailsDTO

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): CastCreditsDTO

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): ReviewsResponseDTO
}
