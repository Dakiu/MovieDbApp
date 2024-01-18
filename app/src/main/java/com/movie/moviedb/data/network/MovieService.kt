package com.movie.moviedb.data.network

import com.movie.moviedb.data.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("3/movie/popular")
    suspend fun getAllMovie(@Query("api_key") apiKey: String, @Query("page") page: Int): Movie
}