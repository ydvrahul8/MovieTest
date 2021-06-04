package com.example.moviedetail.di.popular_movies

import com.example.moviedetail.base.OnClickHandler
import com.example.moviedetail.network.APIRequests
import com.example.moviedetail.repository.PopularMovieRepository
import com.example.moviedetail.view.popular_movies.PopularMoviesAdapter
import dagger.Module
import dagger.Provides

@Module
object PopularMoviesModule {
    @PopularMoviesScope
    @Provides
    @JvmStatic
    fun providePopularMoviesRepository(apiRequests: APIRequests): PopularMovieRepository {
        return PopularMovieRepository(apiRequests)
    }

    @PopularMoviesScope
    @Provides
    @JvmStatic
    fun providePopularMoviesAdapter(onClickHandler: OnClickHandler): PopularMoviesAdapter {
        return PopularMoviesAdapter(onClickHandler)
    }
}