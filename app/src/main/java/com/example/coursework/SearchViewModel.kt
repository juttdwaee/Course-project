package com.example.coursework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovieRepository(application)

    private val _searchResults = MutableLiveData<List<Movie>>()
    val searchResults: LiveData<List<Movie>> = _searchResults

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val API_KEY = "ab42f2f9"

    fun searchMovies(query: String) {
        if (query.isEmpty()) {
            _errorMessage.value = "Введите название фильма"
            return
        }

        _isLoading.value = true

        val call = RetrofitClient.apiService.searchMovies(
            apiKey = API_KEY,
            searchQuery = query
        )

        call.enqueue(object : Callback<MovieSearchResponse> {
            override fun onResponse(
                call: Call<MovieSearchResponse>,
                response: Response<MovieSearchResponse>
            ) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    val movies = response.body()?.search ?: emptyList()
                    _searchResults.value = movies

                    if (movies.isEmpty()) {
                        _errorMessage.value = "Фильмы не найдены"
                    }
                } else {
                    _errorMessage.value = "Ошибка при поиске"
                }
            }

            override fun onFailure(
                call: Call<MovieSearchResponse>,
                t: Throwable
            ) {
                _isLoading.value = false
                _errorMessage.value = "Ошибка сети: ${t.message}"
            }
        })
    }

    fun saveMovie(movie: Movie) {
        GlobalScope.launch(Dispatchers.Main) {
            repository.insertMovie(movie)
            _errorMessage.value = "Фильм сохранен!"
        }
    }
}
