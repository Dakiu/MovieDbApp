package com.movie.moviedb

import com.movie.moviedb.data.local.movie.MovieDatabase
import com.movie.moviedb.data.local.movie.MovieEntity
import com.movie.moviedb.data.model.Movie
import com.movie.moviedb.data.model.MovieInfo
import com.movie.moviedb.data.network.MovieService
import com.movie.moviedb.repository.MovieRepository
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`


class MovieRepositoryTest {

    private val movieService = mock(MovieService::class.java)
    private val movieDatabase = mock(MovieDatabase::class.java)
    private val movieRepository = MovieRepository(movieService, movieDatabase)

    @Test
    fun `getMovie local`() = runBlockingTest {

        val apiKey = "your_api_key"
        val page = 1
        val movieEntity = mock(MovieEntity::class.java)
        val localMovies = listOf(movieEntity)

        `when`(movieDatabase.movieDao.getMovieList()).thenReturn(localMovies)
        val result = movieRepository.getMovie(apiKey, page)

        assertEquals(localMovies, result.results)
    }

    @Test
    fun `getMovie internet `() = runBlockingTest {

        val apiKey = "your_api_key"
        val page = 1

        val movies = MovieInfo(1,"title","overview","path","01/01",1.1,1,false,1.1,"es")
        val movieEntity = MovieEntity(1,"title","overview","path","01/01",1.1,1,false,1.1,"es")
        val networkMovies = listOf(movies)
        val remoteMovies = Movie(results = networkMovies, page = 1, total_pages = 1, total_results = 30)

        `when`(movieDatabase.movieDao.getMovieList()).thenReturn(listOf(movieEntity))
        `when`(movieService.getAllMovie(apiKey, page)).thenReturn(remoteMovies)
        `when`(movieRepository.getMovie(apiKey, page)).thenReturn(remoteMovies)

        val result = movieRepository.getMovie(apiKey, page)
        assertEquals(remoteMovies, result)
    }
}