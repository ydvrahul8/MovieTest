package com.example.moviedetail.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MovieDBResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    @SerializedName("results")
    @Expose
    val Movies: List<Movie>
) : Parcelable