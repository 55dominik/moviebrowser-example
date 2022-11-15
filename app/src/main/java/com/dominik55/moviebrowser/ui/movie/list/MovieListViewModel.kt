package com.dominik55.moviebrowser.ui.movie.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dominik55.moviebrowser.domain.MovieInteractor
import com.dominik55.moviebrowser.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieInteractor: MovieInteractor
) : ViewModel() {

    companion object {
        private const val TAG = "MovieListViewModel"
    }

    private val disposables = CompositeDisposable()

    private var _movies: MutableLiveData<List<Movie>> = MutableLiveData()
    val movies: LiveData<List<Movie>>
        get() = _movies

    fun loadMovies() {
        movieInteractor
            .getTrendingMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { _movies.value = it },
                onError = { Log.d(TAG, it.stackTraceToString()) }
            )
            .addTo(disposables)
    }

    override fun onCleared() {
        disposables.clear()

        super.onCleared()
    }
}
