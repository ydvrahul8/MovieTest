package com.example.moviedetail.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.moviedetail.network.APIRequests
import javax.inject.Inject

class PopularMovieRepository @Inject constructor(private val apiRequests: APIRequests) {

    fun getSearchResults() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(apiRequests) }
        ).liveData
}