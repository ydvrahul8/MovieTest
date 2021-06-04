package com.example.moviedetail.model.babySize

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BabySize(
    val gallery: List<Gallery>
):Parcelable