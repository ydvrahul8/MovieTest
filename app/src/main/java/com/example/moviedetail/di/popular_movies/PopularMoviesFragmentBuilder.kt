package com.example.moviedetail.di.popular_movies

import com.example.moviedetail.view.popular_movies.PopularMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface PopularMoviesFragmentBuilder {
    @PopularMoviesScope
    @ContributesAndroidInjector(
        modules = [
            PopularMoviesViewModelModule::class
        ]
    )
    fun contributeSplashFragment(): PopularMoviesFragment
}