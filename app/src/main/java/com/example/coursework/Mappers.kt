package com.example.coursework

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        year = year,
        type = type,
        poster = poster,
        plot = plot ?: ""
    )
}

fun Movie.toEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        year = year,
        type = type,
        poster = poster,
        plot = plot
    )
}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        year = year,
        type = type,
        poster = poster,
        plot = plot
    )
}