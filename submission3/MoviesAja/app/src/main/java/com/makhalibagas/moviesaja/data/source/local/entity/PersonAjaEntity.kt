package com.makhalibagas.moviesaja.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_person")
data class PersonAjaEntity(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int? = null,

        @NonNull
        @ColumnInfo(name = "personId")
        var personId: Int = 0,

        @ColumnInfo(name = "profile")
        var profilePath: String? = null,


        )