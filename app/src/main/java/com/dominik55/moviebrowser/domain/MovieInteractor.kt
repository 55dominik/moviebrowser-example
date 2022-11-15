package com.dominik55.moviebrowser.domain

import com.dominik55.moviebrowser.data.network.MovieApi
import com.dominik55.moviebrowser.domain.model.Movie
import com.dominik55.moviebrowser.domain.model.toDomainMovie
import io.reactivex.Observable
import io.reactivex.rxkotlin.flatMapIterable
import javax.inject.Inject

class MovieInteractor @Inject constructor(
    private val movieApi: MovieApi
) {

    fun getTrendingMovies(): Observable<List<Movie>> {
        val initialResultList = mutableListOf<Movie>()
        return movieApi.getTrendingMovies()
            .map { it.results }
            .doOnNext { results ->
                initialResultList.addAll(results.map { it.toDomainMovie() })
            }
            .flatMapIterable()
            .flatMap { getMovieDetails(it.id.toLong()) }
            .map { movie ->
                val pos = initialResultList.indexOfFirst {
                    it.id == movie.id
                }
                initialResultList[pos] = movie
                initialResultList
            }
    }

    fun getMovieDetails(id: Long): Observable<Movie> {
        return movieApi.getMovie(id).map { it.toDomainMovie() }
    }
}