package com.movie.moviedb.data.local.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieEntity")
data class MovieEntity (
    @PrimaryKey
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Double,
    val vote_count: Int,
    val adult: Boolean,
    val popularity: Double,
    val original_language: String,
)