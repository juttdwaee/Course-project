package com.example.coursework

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = viewModel(),
    onSaveMovie: (Movie) -> Unit = {},
    onNavigateToSaved: () -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    val searchResults by viewModel.searchResults.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Поиск фильмов",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Название фильма") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            singleLine = true
        )

        Button(
            onClick = { viewModel.searchMovies(searchQuery) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("Искать")
        }

        Button(
            onClick = { onNavigateToSaved() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text("Мои фильмы")
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.padding(16.dp)
            )
        }

        if (errorMessage.isNotEmpty() && !isLoading) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
        }

        LazyColumn {
            items(searchResults) { movie ->
                MovieItem(
                    movie = movie,
                    onSave = {
                        viewModel.saveMovie(movie)
                        onSaveMovie(movie)
                    }
                )
            }
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    onSave: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Год: ${movie.year}",
                style = MaterialTheme.typography.bodySmall
            )

            if (movie.plot.isNotEmpty()) {
                Text(
                    text = "Описание: ${movie.plot.take(100)}...",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Button(
                onClick = { onSave() },
                modifier = Modifier
                    .align(androidx.compose.ui.Alignment.End)
                    .padding(top = 8.dp)
            ) {
                Text("Сохранить")
            }
        }
    }
}
