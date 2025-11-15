package com.example.coursework

import com.google.gson.annotations.SerializedName

data class MovieSearchResponse(
    @SerializedName("Search")
    val search: List<Movie> = emptyList(),

    @SerializedName("totalResults")
    val totalResults: String = "0",

    @SerializedName("Response")
    val response: String = "True"
)
