package com.example.coursework

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("imdbID") val id: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("Plot") val plot: String?
)

data class MovieSearchResponseDto(
    @SerializedName("Search") val search: List<MovieDto> = emptyList(),
    @SerializedName("Response") val response: String = "True"
)