package com.example.reviewnest_mvvm.network.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TMDbApiClient {
    val api: TMDbApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TMDbApi::class.java)
    }
}
