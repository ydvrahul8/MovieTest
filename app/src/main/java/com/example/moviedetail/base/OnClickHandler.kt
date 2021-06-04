package com.example.moviedetail.base

import android.content.Intent
import android.view.View
import com.example.moviedetail.model.Movie
import com.example.moviedetail.utils.MOVIE
import com.example.moviedetail.view.movie_details.MovieDetailActivity
import javax.inject.Inject


class OnClickHandler @Inject constructor() {
    fun movieClick(view: View, movie: Movie) {
        val intent = Intent(view.context, MovieDetailActivity::class.java)
        intent.putExtra(MOVIE, movie)
        view.context.startActivity(intent)
    }
}