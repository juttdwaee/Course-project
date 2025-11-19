package com.example.coursework

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SavedMoviesScreen(
    viewModel: SavedMoviesViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val movies by viewModel.savedMovies.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = stringResource(R.string.saved_title),
            style = MaterialTheme.typography.headlineMedium
        )

        Button(
            onClick = onNavigateBack,
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
        ) {
            Text(stringResource(R.string.btn_back))
        }

        if (movies.isEmpty()) {
            Text(stringResource(R.string.no_saved_movies))
        } else {
            LazyColumn {
                items(movies) { movie ->
                    MovieItem(
                        movie = movie,
                        onAction = { viewModel.deleteMovie(movie) },
                        isDelete = true
                    )
                }
            }
        }
    }
}