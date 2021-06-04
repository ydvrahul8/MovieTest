package com.example.moviedetail.network

import com.example.moviedetail.model.MovieDBResponse
import com.example.moviedetail.model.MovieDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIRequests {
    @GET("movie/popular")
    suspend fun getPopularMoviesWithPaging(
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int
    ): MovieDBResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path(value = "movie_id") movie_id: String,
        @Query("api_key") apiKey: String?
    ): MovieDetail

}