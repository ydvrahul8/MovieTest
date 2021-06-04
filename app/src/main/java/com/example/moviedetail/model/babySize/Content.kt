package com.example.moviedetail.model.babySize

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Content(
    val size: String,
    val subtitle: String,
    val title: String,
    val weight: String
):Parcelable