package com.example.moviedetail.di.popular_movies

import androidx.lifecycle.ViewModel
import com.example.moviedetail.di.viewmodel.ViewModelKey
import com.example.moviedetail.view.popular_movies.PopularMoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PopularMoviesViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PopularMoviesViewModel::class)
    abstract fun bindPopularMoviesViewModel(viewModel: PopularMoviesViewModel): ViewModel

}