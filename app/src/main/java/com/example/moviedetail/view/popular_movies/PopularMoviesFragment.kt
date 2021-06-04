package com.example.moviedetail.view.popular_movies

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.LoadState
import com.example.moviedetail.R
import com.example.moviedetail.base.BaseFragment
import com.example.moviedetail.databinding.FragmentPopularMoviesBinding
import com.example.moviedetail.di.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class PopularMoviesFragment : BaseFragment<FragmentPopularMoviesBinding>() {
    override val layout: Int
        get() = R.layout.fragment_popular_movies

    @Inject
    lateinit var factory: ViewModelProviderFactory

    @Inject
    lateinit var adapter: PopularMoviesAdapter
    private val viewModel by viewModels<PopularMoviesViewModel> { factory }
    private lateinit var binding: FragmentPopularMoviesBinding
    override fun init(bind: FragmentPopularMoviesBinding) {
        binding = bind

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = PopularMovieLoadStateAdapter { adapter.retry() },
                footer = PopularMovieLoadStateAdapter { adapter.retry() }
            )
            buttonRetry.setOnClickListener { adapter.retry() }
        }

        viewModel.popularMovies.observe(viewLifecycleOwner, Observer {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        })

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error

                // empty view
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    recyclerView.isVisible = false
                    textViewEmpty.isVisible = true
                } else {
                    textViewEmpty.isVisible = false
                }
            }
        }

        setHasOptionsMenu(true)
    }

}