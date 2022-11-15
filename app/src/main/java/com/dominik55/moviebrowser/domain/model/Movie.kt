package com.dominik55.moviebrowser.domain.model

import com.dominik55.moviebrowser.data.network.model.MovieDetailsResponse
import com.dominik55.moviebrowser.data.network.model.MovieResult

data class Movie(
    val id: Long,
    val title: String,
    val overview: String?,
    val coverUrl: String?,
    val rating: Float,
    val genres: List<String>
)

fun MovieResult.toDomainMovie(genres: List<String> = listOf()) = Movie(
    id = id.toLong(),
    title = title,
    overview = overview,
    coverUrl = posterPath,
    rating = voteAverage.toFloat(),
    genres = genres
)

fun MovieDetailsResponse.toDomainMovie() = Movie(
    id = id.toLong(),
    title = title,
    overview = overview,
    coverUrl = posterPath,
    rating = voteAverage.toFloat(),
    genres = genres.map { it.name }
)
