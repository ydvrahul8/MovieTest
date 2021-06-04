package com.example.moviedetail.view.movie_details

import android.os.Bundle
import androidx.navigation.findNavController
import com.example.moviedetail.R
import com.example.moviedetail.base.BaseActivity
import com.example.moviedetail.databinding.ActivityMovieDetailBinding
import com.example.moviedetail.model.Movie
import com.example.moviedetail.utils.MOVIE

class MovieDetailActivity : BaseActivity<ActivityMovieDetailBinding>() {
    override val layout: Int
        get() = R.layout.activity_movie_detail
    override var title: String ="MovieDetail"


    override fun init(bind: ActivityMovieDetailBinding) {
        val movie = intent.getParcelableExtra<Movie>(MOVIE)
        val navController = findNavController(R.id.fragment_movie_detail)
        navController.setGraph(R.navigation.movie_detail_graph,MovieDetailFragmentArgs(movie!!).toBundle())
        title = movie.title
    }
}