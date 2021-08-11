package com.makhalibagas.moviesaja.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MoviesTvAja(
        var id: Int? = null,
        var name: String? = null,
        var desc: String? = null,
        var poster: String? = null,
        var backdrop: String? = null,
) : Parcelable