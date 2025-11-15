package com.example.coursework

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(application: Application) {

    private val movieDao: MovieDao =
        MovieDatabase.getDatabase(application).movieDao()

    val allMovies: LiveData<List<Movie>> = movieDao.getAllMovies()

    suspend fun insertMovie(movie: Movie) {
        withContext(Dispatchers.IO) {
            movieDao.insert(movie)
        }
    }

    suspend fun deleteMovie(movie: Movie) {
        withContext(Dispatchers.IO) {
            movieDao.delete(movie)
        }
    }

    fun getMovieById(movieId: String): LiveData<Movie> {
        return movieDao.getMovieById(movieId)
    }
}
