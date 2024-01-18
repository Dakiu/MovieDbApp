package com.movie.moviedb.repository

import com.movie.moviedb.data.local.movie.MovieDatabase
import com.movie.moviedb.data.mapper.toMovieEntity
import com.movie.moviedb.data.mapper.toMovieInfo
import com.movie.moviedb.data.model.Movie
import com.movie.moviedb.data.network.MovieService
import javax.inject.Inject

class MovieRepository @Inject constructor(val movieService: MovieService, val movieDatabase: MovieDatabase) {

    suspend fun getMovie(apiKey: String, page:Int): Movie {


        if (movieDatabase.movieDao.getMovieList().isEmpty()){

            val moviesFromInternet = movieService.getAllMovie(apiKey, page).results.let{
                it.map{ movieInet ->
                    movieInet.toMovieEntity()
                }
            }

            movieDatabase.movieDao.upsertMovieList(moviesFromInternet)
            return movieService.getAllMovie(apiKey, page)

        }else{

            val moviesFromLocal = movieDatabase.movieDao.getMovieList().let{
                it.map{ movieInet ->
                    movieInet.toMovieInfo()
                }
            }

            val movies = Movie(results = moviesFromLocal, page = 1, total_pages = 42351, total_results = 847015)
            return movies
        }

    }
}