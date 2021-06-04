package com.example.moviedetail.di.movie_details

import androidx.lifecycle.ViewModel
import com.example.moviedetail.di.viewmodel.ViewModelKey
import com.example.moviedetail.view.movie_details.MovieDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MovieDetailViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindMovieDetailViewModel(viewModel: MovieDetailViewModel): ViewModel

}