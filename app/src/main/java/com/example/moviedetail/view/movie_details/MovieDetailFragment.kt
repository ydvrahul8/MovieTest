package com.example.moviedetail.view.movie_details

import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.moviedetail.R
import com.example.moviedetail.base.BaseFragment
import com.example.moviedetail.databinding.FragmentMovieDetailBinding
import com.example.moviedetail.di.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {
    override val layout: Int
        get() = R.layout.fragment_movie_detail
    private val args: MovieDetailFragmentArgs by navArgs()

    @Inject
    lateinit var factory: ViewModelProviderFactory
    private val viewModel: MovieDetailViewModel by activityViewModels { factory }
    override fun init(bind: FragmentMovieDetailBinding) {
        val movie = args.movie
        Log.e("Movie Detail Fragment", "init: $movie")
        viewModel.getMovieDetail(movie?.id.toString())

        viewModel.data.observe(requireActivity(), Observer {
            bind.loadingLayout.visibility = View.VISIBLE
            bind.progressBar.visibility = View.VISIBLE
            if (it != null) {
                bind.movie = it
                bind.loadingLayout.visibility = View.GONE
            } else {
                bind.progressBar.visibility = View.GONE
                bind.textViewError.visibility = View.VISIBLE
                bind.buttonRetry.visibility = View.VISIBLE
                bind.buttonRetry.setOnClickListener {
                    viewModel.getMovieDetail(movie?.id.toString())
                }
            }
        })
    }

}