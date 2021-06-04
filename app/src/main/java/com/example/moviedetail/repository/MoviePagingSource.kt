package com.example.moviedetail.repository


import androidx.paging.PagingSource
import com.example.moviedetail.model.Movie
import com.example.moviedetail.network.APIRequests
import com.example.moviedetail.utils.API_KEY
import retrofit2.HttpException
import java.io.IOException

private const val MOVIE_STARTING_PAGE_INDEX = 1

class MoviePagingSource(
    private val apiRequests: APIRequests
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: MOVIE_STARTING_PAGE_INDEX

        return try {
            val response = apiRequests.getPopularMoviesWithPaging(API_KEY, position)
            val movies = response.Movies

            LoadResult.Page(
                data = movies!!,
                prevKey = if (position == MOVIE_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}