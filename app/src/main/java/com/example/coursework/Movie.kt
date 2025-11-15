package com.example.coursework

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    @SerializedName("imdbID")
    val id: String = "",

    @SerializedName("Title")
    val title: String = "",

    @SerializedName("Year")
    val year: String = "",

    @SerializedName("Type")
    val type: String = "",

    @SerializedName("Poster")
    val poster: String = "",

    @SerializedName("Plot")
    val plot: String = ""
)
