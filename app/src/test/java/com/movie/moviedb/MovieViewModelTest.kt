package com.movie.moviedb


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.movie.moviedb.data.model.Movie
import com.movie.moviedb.data.model.MovieInfo
import com.movie.moviedb.repository.MovieRepository
import com.movie.moviedb.ui.viewmodel.MovieViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when`


@ExperimentalCoroutinesApi
 class MovieViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var movieRepository: MovieRepository
    private lateinit var movieObserver: Observer<Movie>
    private lateinit var movieInfoObserver: Observer<MovieInfo>
    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        movieRepository = mock(MovieRepository::class.java)
        movieObserver = mock(Observer::class.java) as Observer<Movie>
        movieInfoObserver = mock(Observer::class.java) as Observer<MovieInfo>

        movieViewModel = MovieViewModel(movieRepository)
        movieViewModel.movie.observeForever(movieObserver)
        movieViewModel.movieInfo.observeForever(movieInfoObserver)

        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun resetDispatcher() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }


    @Test
    suspend fun fetchMovies(){
        val apiKey = "f6c06b6a1cc549d810d4fb194b9d7633"
        val page = 1
        val movieResult = mock(Movie::class.java)

        `when`(movieRepository.getMovie(apiKey, page)).thenReturn(movieResult)

        movieViewModel.fetchMovies(apiKey, page)

        verify(movieRepository).getMovie(apiKey, page)
        verify(movieObserver).onChanged(movieResult)
    }

    @Test
    fun `setMovieInfo sets movieInfo LiveData with provided MovieInfo`() {

        val movieInfo = mock(MovieInfo::class.java)
        movieViewModel.setMovieInfo(movieInfo)
        verify(movieInfoObserver).onChanged(movieInfo)
    }

}
