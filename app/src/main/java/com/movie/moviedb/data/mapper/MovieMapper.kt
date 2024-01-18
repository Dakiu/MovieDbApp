package com.movie.moviedb.data.mapper

import com.movie.moviedb.data.local.movie.MovieEntity
import com.movie.moviedb.data.model.MovieInfo

fun MovieInfo.toMovieEntity():MovieEntity{
    return  MovieEntity(
        id = id,
        title = title,
        overview = overview,
        poster_path = poster_path,
        release_date = release_date,
        vote_average = vote_average,
        vote_count = vote_count,
        adult = adult,
        popularity = popularity,
        original_language = original_language
    )
}

fun MovieEntity.toMovieInfo():MovieInfo{
    return  MovieInfo(
        id = id,
        title = title,
        overview = overview,
        poster_path = poster_path,
        release_date = release_date,
        vote_average = vote_average,
        vote_count = vote_count,
        adult = adult,
        popularity = popularity,
        original_language = original_language
    )
}
