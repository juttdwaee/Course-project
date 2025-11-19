package com.example.coursework

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: String,
    val title: String,
    val year: String,
    val type: String,
    val poster: String,
    val plot: String
)