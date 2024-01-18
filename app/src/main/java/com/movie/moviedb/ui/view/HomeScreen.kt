package com.movie.moviedb.ui.view

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.movie.moviedb.data.model.MovieInfo
import com.movie.moviedb.ui.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MovieViewModel, navController: NavController) {

    var movies by remember { mutableStateOf(listOf<MovieInfo>()) }

    viewModel.fetchMovies("f6c06b6a1cc549d810d4fb194b9d7633", 1)
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    val moviesList by viewModel.moviesList.collectAsState()

    viewModel.movie.observeForever {
        movies = it.results
    }


    Column(
        Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(4.dp)) {
        Text(
            text = "Movie DB App",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        SearchBar(
            query = searchText,
            onQueryChange = viewModel::onSearchTextChange,
            onSearch = viewModel::onSearchTextChange,
            active = isSearching,
            onActiveChange = { viewModel.onToogleSearch() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            LazyColumn {

                items(movies) { movie ->
                    Text(
                        text = movie.title,
                        modifier = Modifier.padding(
                            start = 8.dp,
                            top = 4.dp,
                            end = 8.dp,
                            bottom = 4.dp)
                    )
                }
            }
        }

        LazyColumn {
            items(movies) {
                ThreeColumnItem(movie = it, viewModel = viewModel, navController)
            }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ThreeColumnItem(movie: MovieInfo, viewModel: MovieViewModel, navController: NavController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                viewModel.setMovieInfo(movieInfo = movie)
                navController.navigate("details")
            },
    ) {

        GlideImage(
            model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
            contentDescription = "Poster",
            Modifier
                .width(150.dp)
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(4.dp)){
            Text(text = "Title", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
            Text(text = "Release Date", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(text = movie.release_date)
            Text(text = "Average Rating", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(text = ""+movie.vote_average)
        }

        Column(
            Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(4.dp)) {
            Text(text = ""+movie.vote_average, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

        }
    }
}