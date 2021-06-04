package com.example.moviedetail.di

import com.example.moviedetail.di.movie_details.MovieDetailFragmentBuilder
import com.example.moviedetail.di.movie_details.MovieDetailScope
import com.example.moviedetail.di.popular_movies.PopularMoviesFragmentBuilder
import com.example.moviedetail.di.popular_movies.PopularMoviesScope
import com.example.moviedetail.view.movie_details.MovieDetailActivity
import com.example.moviedetail.view.popular_movies.PopularMoviesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
        modules = [
            PopularMoviesFragmentBuilder::class
        ]
    )
    abstract fun contributeMainActivity(): PopularMoviesActivity

    @ContributesAndroidInjector(
        modules = [
            MovieDetailFragmentBuilder::class
        ]
    )
    abstract fun contributeMovieDetailActivity(): MovieDetailActivity

}