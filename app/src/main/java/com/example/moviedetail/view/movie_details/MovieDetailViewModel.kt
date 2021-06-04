package com.example.moviedetail.view.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedetail.repository.MovieDetailRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(val movieDetailRepository: MovieDetailRepository) :
    ViewModel() {

    val data = movieDetailRepository.data

    fun getMovieDetail(id: String) {
        viewModelScope.launch { movieDetailRepository.getMovieDetail(id) }
    }
}
