package com.example.moviedetail.di.movie_details

import com.example.moviedetail.view.movie_details.MovieDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MovieDetailFragmentBuilder {
    @MovieDetailScope
    @ContributesAndroidInjector(
        modules =
        [
            MovieDetailViewModelModule::class,
            MovieDetailModule::class
        ]
    )
    fun contributeMovieDetailFragment(): MovieDetailFragment
}