package com.makhalibagas.moviesaja.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.makhalibagas.moviesaja.data.MoviesTvAja
import com.makhalibagas.moviesaja.data.source.MoviesAjaRepository


class DetailViewModel(private val moviesAjaRepository: MoviesAjaRepository)  : ViewModel(){

    fun getDetailMovies(id : Int) : LiveData<MoviesTvAja> = moviesAjaRepository.getDetailMovies(id)
    fun getDetailTv(id : Int) : LiveData<MoviesTvAja> = moviesAjaRepository.getDetailTv(id)

}