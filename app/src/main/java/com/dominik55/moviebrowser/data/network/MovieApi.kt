package com.dominik55.moviebrowser.data.network

import com.dominik55.moviebrowser.data.network.model.MovieDetailsResponse
import com.dominik55.moviebrowser.data.network.model.MovieListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("movie/popular")
    fun getTrendingMovies(): Observable<MovieListResponse>

    @GET("movie/{id}")
    fun getMovie(
        @Path("id") id: Long
    ): Observable<MovieDetailsResponse>
}