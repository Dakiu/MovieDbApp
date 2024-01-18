package com.movie.moviedb.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.moviedb.data.model.Movie
import com.movie.moviedb.data.model.MovieInfo
import com.movie.moviedb.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(val movieRepository: MovieRepository): ViewModel(){

    val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie
    //
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    val _moviesList = MutableStateFlow(_movie.value?.results)
    val moviesList = searchText
    .combine(_moviesList) { text, movies ->
        if (text.isBlank()) {
            movies
        }
        movies?.filter { movie ->
            movie.title.uppercase().contains(text.trim().uppercase())
        }
    }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5000),
    initialValue = _moviesList.value
    )


    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }
//
    fun fetchMovies(apiKey: String, page:Int){
        viewModelScope.launch {
            _movie.value = movieRepository.getMovie(apiKey = apiKey, page = page)
        }
    }

    val _movieInfo = MutableLiveData<MovieInfo>()
    val movieInfo: LiveData<MovieInfo>
        get() = _movieInfo

    fun setMovieInfo(movieInfo: MovieInfo){
        _movieInfo.value = movieInfo
    }
}