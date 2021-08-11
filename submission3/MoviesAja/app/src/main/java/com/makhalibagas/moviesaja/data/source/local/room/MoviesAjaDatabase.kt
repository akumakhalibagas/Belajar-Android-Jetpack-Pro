package com.makhalibagas.moviesaja.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.makhalibagas.moviesaja.data.source.local.entity.MoviesAjaEntity
import com.makhalibagas.moviesaja.data.source.local.entity.TvShowsAjaEntity

@Database(
    entities = [MoviesAjaEntity::class, TvShowsAjaEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MoviesAjaDatabase : RoomDatabase() {

    abstract fun moviesAjaDao(): MoviesAjaDao

    companion object {

        @Volatile
        private var instance: MoviesAjaDatabase? = null

        fun getInstance(context: Context?): MoviesAjaDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context!!.applicationContext,
                    MoviesAjaDatabase::class.java,
                    "moviesAja.db"
                ).build()
            }
    }
}