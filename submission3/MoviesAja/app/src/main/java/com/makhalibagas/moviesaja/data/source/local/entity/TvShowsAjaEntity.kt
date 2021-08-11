package com.makhalibagas.moviesaja.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_tvshow")
data class TvShowsAjaEntity(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int? = 0,

        @NonNull
        @ColumnInfo(name = "tvId")
        var tvId: Int? = 0,

        @ColumnInfo(name = "name")
        var name: String? = null,

        @ColumnInfo(name = "overview")
        var overview: String? = null,

        @ColumnInfo(name = "vote_count")
        var voteCount: Int? = 0,

        @ColumnInfo(name = "popularity")
        var popularity: Double? = 0.0,

        @ColumnInfo(name = "release")
        var release: String? = null,

        @ColumnInfo(name = "backdrop")
        var backdrop: String? = null,

        @ColumnInfo(name = "poster")
        var poster: String? = null,

        @NonNull
        @ColumnInfo(name = "isFavorite")
        var isFavorite: Boolean = false

)