package com.example.reviewnest_mvvm.network.dto

import com.google.gson.annotations.SerializedName

data class ReviewsResponseDTO(
    @SerializedName("results")
    val reviews: List<ReviewsDTO>
)

data class ReviewsDTO(
    @SerializedName("author")
    val author: String,

    @SerializedName("content")
    val content: String
)
