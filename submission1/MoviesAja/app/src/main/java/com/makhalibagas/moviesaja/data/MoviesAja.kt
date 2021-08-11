package com.makhalibagas.moviesaja.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesAja(
        var id: Int,
        var name: String,
        var desc: String,
        var poster: Int,
        var img_preview: Int
) : Parcelable