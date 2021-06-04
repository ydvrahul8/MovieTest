package com.example.moviedetail.model.babySize

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gallery(
    val content: Content,
    val image: String,
    val week: Int
):Parcelable