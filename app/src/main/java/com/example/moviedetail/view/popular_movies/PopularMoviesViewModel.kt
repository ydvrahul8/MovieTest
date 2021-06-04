package com.example.moviedetail.view.popular_movies

import androidx.lifecycle.ViewModel
import com.example.moviedetail.repository.PopularMovieRepository
import javax.inject.Inject

class PopularMoviesViewModel @Inject constructor(popularMovieRepository: PopularMovieRepository) : ViewModel() {
    val popularMovies = popularMovieRepository.getSearchResults()
}