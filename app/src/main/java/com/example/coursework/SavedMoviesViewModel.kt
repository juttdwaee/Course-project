package com.example.coursework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch

class SavedMoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovieRepository(application)

    val allMovies: LiveData<List<Movie>> = repository.allMovies

    fun deleteMovie(movie: Movie) {
        kotlinx.coroutines.GlobalScope.launch {
            repository.deleteMovie(movie)
        }
    }
}
