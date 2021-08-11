package com.makhalibagas.moviesaja.di

import android.content.Context
import com.makhalibagas.moviesaja.data.source.MoviesAjaRepository
import com.makhalibagas.moviesaja.data.source.local.LocalDataSource
import com.makhalibagas.moviesaja.data.source.local.room.MoviesAjaDatabase
import com.makhalibagas.moviesaja.data.source.remote.RemoteDataSource

object Injection {

    fun provideMoviesAjaRepository(context: Context?): MoviesAjaRepository {
        val database = MoviesAjaDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.moviesAjaDao())

        return MoviesAjaRepository.getInstance(remoteDataSource, localDataSource)
    }

}