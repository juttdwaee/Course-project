package com.example.coursework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    private val _searchResults = MutableStateFlow<List<Movie>>(emptyList())
    val searchResults: StateFlow<List<Movie>> = _searchResults.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()

    fun searchMovies(query: String) {
        if (query.isBlank()) return

        viewModelScope.launch {
            _isLoading.value = true
            _message.value = null

            repository.searchMovies(query)
                .onSuccess { movies ->
                    _searchResults.value = movies
                    if (movies.isEmpty()) _message.value = "No results found"
                }
                .onFailure { e ->
                    _message.value = e.localizedMessage ?: "Error"
                }

            _isLoading.value = false
        }
    }

    fun saveMovie(movie: Movie) {
        viewModelScope.launch {
            repository.saveMovie(movie)
            _message.value = "Saved!"
        }
    }

    private fun saveMovie(movie: Any) {}

    fun clearMessage() {
        _message.value = null
    }
}