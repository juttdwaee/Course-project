package com.example.coursework

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SavedMoviesScreen(
    viewModel: SavedMoviesViewModel = viewModel(),
    onNavigateBack: () -> Unit = {}
) {
    val movies by viewModel.allMovies.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Сохраненные фильмы",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = { onNavigateBack() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("Вернуться к поиску")
        }

        if (movies.isEmpty()) {
            Text(
                text = "Нет сохраненных фильмов",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyColumn {
                items(movies) { movie ->
                    SavedMovieItem(
                        movie = movie,
                        onDelete = { viewModel.deleteMovie(movie) }
                    )
                }
            }
        }
    }
}

@Composable
fun SavedMovieItem(
    movie: Movie,
    onDelete: () -> Unit
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
                text = "Год: ${movie.year} | Тип: ${movie.type}",
                style = MaterialTheme.typography.bodySmall
            )

            if (movie.plot.isNotEmpty()) {
                Text(
                    text = "Описание: ${movie.plot}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Button(
                onClick = { onDelete() },
                modifier = Modifier
                    .align(androidx.compose.ui.Alignment.End)
                    .padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Удалить")
            }
        }
    }
}
