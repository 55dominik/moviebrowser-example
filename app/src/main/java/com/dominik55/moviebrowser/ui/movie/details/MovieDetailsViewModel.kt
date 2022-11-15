package com.dominik55.moviebrowser.ui.movie.details

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
class MovieDetailsViewModel @Inject constructor(
    private val movieInteractor: MovieInteractor
) : ViewModel() {

    companion object {
        private const val TAG = "MovieDetailsViewModel"
    }

    private val disposables = CompositeDisposable()

    private var _movie: MutableLiveData<Movie> = MutableLiveData()
    val movie: LiveData<Movie>
        get() = _movie

    fun loadMovie(id: Long) {
        movieInteractor
            .getMovieDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { _movie.value = it },
                onError = { Log.d(TAG, it.stackTraceToString()) }
            )
            .addTo(disposables)
    }

    override fun onCleared() {
        disposables.clear()

        super.onCleared()
    }
}
