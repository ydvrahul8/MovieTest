package com.example.moviedetail.di.movie_details

import android.app.Application
import com.example.moviedetail.BaseApplication
import com.example.moviedetail.db.MovieDetailDAO
import com.example.moviedetail.db.MovieDetailDatabase
import com.example.moviedetail.network.APIRequests
import com.example.moviedetail.repository.MovieDetailRepository
import dagger.Module
import dagger.Provides

@Module
object MovieDetailModule {
    @MovieDetailScope
    @Provides
    @JvmStatic
    fun provideMovieDao(application: Application): MovieDetailDAO {
        return MovieDetailDatabase.getInstance(application).movieDetailDAO
    }

    @MovieDetailScope
    @Provides
    @JvmStatic
    fun provideMoviesDetailRepository(apiRequests: APIRequests,dao: MovieDetailDAO): MovieDetailRepository {
        return MovieDetailRepository(apiRequests,dao)
    }
}