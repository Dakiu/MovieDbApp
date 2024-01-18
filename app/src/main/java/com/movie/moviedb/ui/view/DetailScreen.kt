package com.movie.moviedb.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.movie.moviedb.ui.viewmodel.MovieViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(viewModel: MovieViewModel, navController: NavController){
    val movieInfo = viewModel.movieInfo.value
    movieInfo?.let {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())) {
            GlideImage(
                model = "https://image.tmdb.org/t/p/w500${movieInfo.poster_path}",
                contentDescription = "PosterPath",
                Modifier.width(300.dp).height(350.dp).align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )
            Text(text = movieInfo.title, style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(8.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                Text(text = "Rating: ${movieInfo.vote_average} Release Date: ${movieInfo.release_date}")
            }
            Text(text = "Overview", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
            Text(text = movieInfo.overview, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp))
            Text(text = "Vote Average", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
            Text(text = movieInfo.vote_average.toString(), style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp))
            Text(text = "Vote Count", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
            Text(text = movieInfo.vote_count.toString(), style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp))
            Text(text = "Popularity", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
            Text(text = movieInfo.popularity.toString(), style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp))
            Text(text = "Original Language", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
            Text(text = movieInfo.original_language, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp))
        }
    }

}