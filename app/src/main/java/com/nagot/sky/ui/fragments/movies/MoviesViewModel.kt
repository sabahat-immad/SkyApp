package com.nagot.sky.ui.fragments.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nagot.sky.R
import com.nagot.sky.data.MoviesRepository
import com.nagot.sky.models.Event
import com.nagot.sky.models.Resource.Success
import com.nagot.sky.models.entity.Movie
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel
@Inject constructor(
    private val mDefaultMoviesRepository: MoviesRepository
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>().apply { value = emptyList() }
    val movies: LiveData<List<Movie>> = _movies

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText

    init {
        loadMovies()
    }

    fun loadMovies() {

        _dataLoading.value = true

        viewModelScope.launch {

            val moviesResult = mDefaultMoviesRepository.getMovies()

            if (moviesResult is Success) {

                val movies = moviesResult.data

                _movies.value = ArrayList(movies)
            } else {

                _movies.value = emptyList()
                showSnackbarMessage(R.string.error)

            }

            _dataLoading.value = false
        }
    }

    fun refresh() {
        loadMovies()
    }

    private fun showSnackbarMessage(message: Int) {
        _snackbarText.value = Event(message)
    }
}