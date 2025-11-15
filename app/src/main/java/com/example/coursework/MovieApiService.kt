package com.example.coursework

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET(".")
    fun searchMovies(
        @Query("apikey") apiKey: String,
        @Query("s") searchQuery: String,
        @Query("type") type: String = "movie"
    ): Call<MovieSearchResponse>

    @GET(".")
    fun getMovieById(
        @Query("apikey") apiKey: String,
        @Query("i") imdbId: String
    ): Call<Movie>
}
