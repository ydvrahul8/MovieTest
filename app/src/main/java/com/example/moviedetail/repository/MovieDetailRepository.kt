package com.example.moviedetail.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.moviedetail.db.MovieDetailDAO
import com.example.moviedetail.model.MovieDetail
import com.example.moviedetail.network.APIRequests
import com.example.moviedetail.utils.API_KEY
import com.example.moviedetail.utils.DEFAULT_ID
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(
    val apiRequests: APIRequests,
    val dao: MovieDetailDAO
) {

    private val movieId = MutableLiveData(DEFAULT_ID)

    var data = movieId.switchMap {
        dao.getMovieDetail(it)
    }

    suspend fun getMovieDetail(id: String) {
        movieId.value = id
        Log.e("Repository", "getMovieDetail: " + dao.isRowIsExist(id))
        if (dao.isRowIsExist(id))
            data = dao.getMovieDetail(id)
        else {
            try {
                val data = apiRequests.getMovieDetails(id, API_KEY)
                insert(data)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun insert(movieDetail: MovieDetail): Long {
        return dao.insertMovieDetail(movieDetail)
    }

}