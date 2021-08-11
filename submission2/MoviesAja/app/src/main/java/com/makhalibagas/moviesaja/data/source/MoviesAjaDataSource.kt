package com.makhalibagas.moviesaja.data.source

import androidx.lifecycle.LiveData
import com.makhalibagas.moviesaja.data.MoviesTvAja

interface MoviesAjaDataSource {

    fun getPopularMovies() : LiveData<List<MoviesTvAja>>
    fun getPopularTv() : LiveData<List<MoviesTvAja>>

    fun getDetailMovies(moviesId : Int) : LiveData<MoviesTvAja>
    fun getDetailTv(tvId : Int) : LiveData<MoviesTvAja>

}