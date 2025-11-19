package com.example.coursework

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val apiService: MovieApiService
) {
    val allMovies: Flow<List<Movie>> = movieDao.getAllMovies().map { entities ->
        entities.map { it.toDomain() }
    }

    suspend fun searchMovies(query: String): Result<List<Movie>> {
        return try {
            val response = apiService.searchMovies(
                apiKey = BuildConfig.OMDB_API_KEY,
                searchQuery = query
            )

            if (response.response == "True") {
                Result.success(response.search.map { it.toDomain() })
            } else {
                Result.success(emptyList())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
    suspend fun saveMovie(movie: Movie) {
        movieDao.insert(movie.toEntity())
    }

    suspend fun deleteMovie(movieId: String) {
        movieDao.deleteById(movieId)
    }
}