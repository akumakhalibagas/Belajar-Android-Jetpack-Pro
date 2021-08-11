package com.makhalibagas.moviesaja.di

import com.makhalibagas.moviesaja.data.source.MoviesAjaRepository
import com.makhalibagas.moviesaja.data.source.remote.RemoteDataSource

object Injection {

    fun provideMoviesAjaRepository() : MoviesAjaRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MoviesAjaRepository.getInstance(remoteDataSource)
    }

}